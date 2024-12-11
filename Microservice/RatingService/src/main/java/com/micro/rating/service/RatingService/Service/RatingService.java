package com.micro.rating.service.RatingService.Service;

import java.util.List;

import com.micro.rating.service.RatingService.entities.Rating;

public interface RatingService {

	Rating createRating(Rating rating);
	
	List<Rating> getAllRating ();
	
	List<Rating> getAllRatingByUserId(String userId);
	
	List<Rating> getAllRatingByHotelId(String hotelId);
	

}
