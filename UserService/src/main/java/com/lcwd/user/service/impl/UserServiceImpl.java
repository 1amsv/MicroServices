package com.lcwd.user.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.feign.external_services.HotelServices;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelServices hotelServices;
	
//	@Autowired
//	private RatingServices ratingServices;

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		// new unique userId every Time
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		// getting user from database with the help of user repository
		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User with given id " + userId + " does not exists on server"));
		// fetch rating of the above user from rating service

		Rating[] ratingOfUser = restTemplate
				.getForObject("http://RATINGSERVICE/ratings/users/" + user.getUserId(), Rating[].class);
		
//		Rating [] ratingOfUser = ratingServices.getRating();// still need to work on this
		
		List<Rating> ratings = Arrays.stream(ratingOfUser).toList();

		List<Rating> ratingList = ratings.stream().map(rating -> {
			// api call to hotel service to get the hotel
			
//			ResponseEntity<Hotel> forEntity = restTemplate
//					.getForEntity("http://HOTELSERVICE/hotels/" + rating.getHotelId(), Hotel.class);			
//			Hotel hotel = forEntity.getBody();

			Hotel hotel = hotelServices.getHotel(rating.getHotelId());
			
			
			
			// set the hotel to rating
			rating.setHotel(hotel);
			
			// return the rating
			return rating;
		}).collect(Collectors.toList());

		user.setRatings(ratingList);

		return user;
	}
//
//	@Override
//	public User updateUser(User user, String id) {
//		// TODO Auto-generated method stub 
//		return null;
//	}
//
//	@Override
//	public User deleteUser(String id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
