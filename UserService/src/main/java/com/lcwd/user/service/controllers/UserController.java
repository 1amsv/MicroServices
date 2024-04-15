package com.lcwd.user.service.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

	// createUser

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User user1 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);

	}
	
	// getSingleserById
	@GetMapping("/{userId}")
	//@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
	//@Retry(name="ratingHotelService", fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name="userRateLimiter", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
		User user = userService.getUser(userId);
		return ResponseEntity.ok(user);
	}

	// creating fallback method for circuit breaker
	public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
		User user = User.builder().email("dummy@mail.com").name("Dummy").about("Dummy user cuz some service is down")
				.userId("abcd1234").build();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	// getAllUsers
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> userList = userService.getAllUser();
		return ResponseEntity.ok(userList);
	}

}
