package com.lcwd.user.service.services;

import java.util.List;

import com.lcwd.user.service.entities.User;

public interface UserService {

	
//User Operations
	
	//createUser
	User saveUser(User user);
	
	//getAllUsers	
	List<User> getAllUser();
	
	//getSingleUserOfGiveId	
	User getUser(String userId);
	
//	//updateUserDetails
//	User updateUser(User user, String id);
//	
//	//deleteUserById
//	User deleteUser(String id);
}
