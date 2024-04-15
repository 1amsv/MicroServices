package com.lcwd.rating.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// @Getter
// @Setter

@NoArgsConstructor
@AllArgsConstructor

@Document("user_rating")
public class Rating {
	@Id
	private String id;
	private String userId;
	private String hotelId;
	private int rating;
	private String feedback;
}
