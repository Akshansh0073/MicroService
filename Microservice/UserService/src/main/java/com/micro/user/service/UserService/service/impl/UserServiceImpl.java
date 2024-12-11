package com.micro.user.service.UserService.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.micro.user.service.UserService.External.Services.HotelService;
import com.micro.user.service.UserService.entities.Hotel;
import com.micro.user.service.UserService.entities.Rating;
import com.micro.user.service.UserService.entities.User;
import com.micro.user.service.UserService.exception.ResourceNotFoundException;
import com.micro.user.service.UserService.repository.UserRepository;
import com.micro.user.service.UserService.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		String randomId = UUID.randomUUID().toString();
		user.setUserId(randomId);
		User save = userRepo.save(user);
		return save;
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public User getUser(String userId) {

		// user
		User user = userRepo.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User with given id is not found on server : " + userId));

		// fetch rating
		// http://localhost:8083/ratings/users/0e549073-4923-44d4-bdfd-5333f253d271
		Rating[] ratingOfUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/" + userId,
				Rating[].class);

		List<Rating> ratings = Arrays.stream(ratingOfUser).toList();

		// fetch hotel by rating and set it into rating

		List<Rating> ratingList = ratings.stream().map(rate -> {

			// http://localhost:8082/hotels/ccc4d40f-1dd0-4506-8249-e990ec0ec0f2
//			ResponseEntity<Hotel> forEntity = restTemplate
//					.getForEntity("http://HOTELSERVICE/hotels/" + rate.getHotelId(), Hotel.class);
			
			Hotel hotel = hotelService.getHotel(rate.getHotelId());

//			logger.info("response status code: {} ", forEntity.getStatusCode());

			// set hotel
			rate.setHotel(hotel);

			// return new rating
			return rate;

		}).collect(Collectors.toList());

		user.setRatings(ratingList);

		logger.info("{} ", ratingList);

		return user;
	}

}
