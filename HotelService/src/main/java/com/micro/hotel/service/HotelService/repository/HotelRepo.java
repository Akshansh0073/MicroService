package com.micro.hotel.service.HotelService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.hotel.service.HotelService.entities.Hotel;

public interface HotelRepo extends JpaRepository<Hotel, String> {

}
