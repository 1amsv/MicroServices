package com.lcwd.user.service.feign.external_services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.lcwd.user.service.entities.Rating;

@FeignClient(name = "RATINGSERVICE")
public interface RatingServices {
	
	@GetMapping("/ratings")
	Rating getRating();	

}
