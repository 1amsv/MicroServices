package com.lcwd.user.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		//new unique userId every Time
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
		 //fetch rating of the above user from rating service
		 
		ArrayList<Rating> forObject = restTemplate.getForObject("http://localhost:9092/ratings/users/"+user.getUserId(),ArrayList.class);
		final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
		logger.info("{}",forObject); 
		user.setRatings(forObject);
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
