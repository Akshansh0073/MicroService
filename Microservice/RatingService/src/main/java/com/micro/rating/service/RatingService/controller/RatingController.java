package com.micro.rating.service.RatingService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.rating.service.RatingService.Service.RatingService;
import com.micro.rating.service.RatingService.entities.Rating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/ratings")
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
	@PostMapping
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
		
		return new ResponseEntity<Rating>
		(ratingService.createRating(rating), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Rating>> getAllRatings() {
		
		return new ResponseEntity<List<Rating>>
		(ratingService.getAllRating(),HttpStatus.OK);
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getAllRatingsByUserlId(@PathVariable String userId) {
		
		return new ResponseEntity<List<Rating>>
		(ratingService.getAllRatingByUserId(userId),HttpStatus.OK);
	}
	
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getAllRatingsByHotelId(@PathVariable String hotelId) {
		
		return new ResponseEntity<List<Rating>>
		(ratingService.getAllRatingByHotelId(hotelId),HttpStatus.OK);
	}
	
}
