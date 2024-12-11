package com.micro.hotel.service.HotelService.Service;

import java.util.List;

import com.micro.hotel.service.HotelService.entities.Hotel;

public interface HotelService {

	
	Hotel createHotel (Hotel hotel);
	
	List<Hotel> getAllHotel ();
	
	Hotel getHotel (String hotelId);
	
	
	
}
