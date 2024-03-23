package com.lcwd.hotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class UserConfig {
	
	// Annotation  
	@Bean
	public RestTemplate restTemplate() 
	{
	 return new RestTemplate();
	}

}
