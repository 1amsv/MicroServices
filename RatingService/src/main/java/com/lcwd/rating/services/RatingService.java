package com.lcwd.rating.services;

import java.util.List;

import com.lcwd.rating.entities.Rating;

public interface RatingService {

	//create	
	Rating createRating(Rating rating);
	
	//getAll ratings	
	List<Rating>getAllRating();	
	
	//getAllByUser
	List<Rating> getRatingByUserId(String userId);
	
	//getAllByHotel
	List<Rating> getRatingByHotelId(String hotelId);
}
