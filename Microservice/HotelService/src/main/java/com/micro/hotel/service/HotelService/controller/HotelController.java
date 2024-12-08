package com.micro.hotel.service.HotelService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.hotel.service.HotelService.Service.HotelService;
import com.micro.hotel.service.HotelService.entities.Hotel;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@PostMapping
	public ResponseEntity<Hotel> createHotel (@RequestBody Hotel hotel){
		
		return new ResponseEntity<Hotel>(hotelService.createHotel(hotel), HttpStatus.CREATED);
	}
	
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getHotel (@PathVariable String hotelId){
		
		return new ResponseEntity<Hotel>(hotelService.getHotel(hotelId), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Hotel>> getAllHotel (){
		return new ResponseEntity<List<Hotel>>(hotelService.getAllHotel(), HttpStatus.OK);
	}
}
