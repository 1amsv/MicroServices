package com.lcwd.hotel.services;

import java.util.List;

import com.lcwd.hotel.entites.Hotel;

public interface HotelService {
	
	//create
	Hotel create(Hotel hotel);	
	
	//getAll	
	List<Hotel> getAll();	
	
	//getSingle
	Hotel getHotelById(String hotelId);
	
	
	

}
