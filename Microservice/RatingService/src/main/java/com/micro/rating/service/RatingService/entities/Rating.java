package com.micro.rating.service.RatingService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

	@Id
	private String ratingId;
	private String userId;
	private String hotelId;
	private String rating;
	private String remark;
}