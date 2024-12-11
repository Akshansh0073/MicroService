package com.micro.rating.service.RatingService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.rating.service.RatingService.entities.Rating;
import java.util.List;


public interface RatingRepo extends JpaRepository<Rating, String> {

	
	List<Rating> findByUserId(String userId);
	List<Rating> findByHotelId(String hotelId);
}
