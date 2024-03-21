package com.lcwd.hotel.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}
	
	public ResourceNotFoundException() {
		super("Resource not found !!");
	}
}
