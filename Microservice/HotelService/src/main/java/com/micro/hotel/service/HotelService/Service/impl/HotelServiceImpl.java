package com.micro.hotel.service.HotelService.Service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.hotel.service.HotelService.Exception.ResourceNotFoundException;
import com.micro.hotel.service.HotelService.Service.HotelService;
import com.micro.hotel.service.HotelService.entities.Hotel;
import com.micro.hotel.service.HotelService.repository.HotelRepo;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepo hotelRepo;
	
	@Override
	public Hotel createHotel(Hotel hotel) {
		String randomId = UUID.randomUUID().toString();
		hotel.setId(randomId);
		return hotelRepo.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotel() {
		// TODO Auto-generated method stub
		return hotelRepo.findAll();
	}

	@Override
	public Hotel getHotel(String hotelId) {
		
		return hotelRepo.findById(hotelId).
				orElseThrow(() -> new ResourceNotFoundException
						("Hotel with given id not found "+hotelId));
	}

}
