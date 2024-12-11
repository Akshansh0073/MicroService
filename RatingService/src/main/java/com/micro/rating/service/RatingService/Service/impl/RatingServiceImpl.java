package com.micro.rating.service.RatingService.Service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.rating.service.RatingService.Service.RatingService;
import com.micro.rating.service.RatingService.entities.Rating;
import com.micro.rating.service.RatingService.repository.RatingRepo;

@Service
public class RatingServiceImpl implements RatingService{

	@Autowired
	private RatingRepo ratingRepo;
	
	@Override
	public Rating createRating(Rating rating) {
	String randomId = UUID.randomUUID().toString();
	rating.setRatingId(randomId);
		return ratingRepo.save(rating);
	}

	@Override
	public List<Rating> getAllRating() {
		
		return ratingRepo.findAll();
	}

	@Override
	public List<Rating> getAllRatingByUserId(String userId) {
		
		return ratingRepo.findByUserId(userId);
	}

	@Override
	public List<Rating> getAllRatingByHotelId(String hotelId) {
		
		return ratingRepo.findByHotelId(hotelId);
	}

}
