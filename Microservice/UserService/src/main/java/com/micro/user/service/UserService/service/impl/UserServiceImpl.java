package com.micro.user.service.UserService.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.micro.user.service.UserService.entities.User;
import com.micro.user.service.UserService.exception.ResourceNotFoundException;
import com.micro.user.service.UserService.repository.UserRepository;
import com.micro.user.service.UserService.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
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
		User user = userRepo.findById(userId).
				orElseThrow(() -> new ResourceNotFoundException
				("User with given id is not found on server : "+userId));
		
		// fetch rating
		// http://localhost:8083/ratings/users/0e549073-4923-44d4-bdfd-5333f253d271
		ArrayList ratings = restTemplate.
				getForObject("http://localhost:8083/ratings/users/0e549073-4923-44d4-bdfd-5333f253d271", ArrayList.class);
		
		user.setRatings(ratings);
		logger.info("{} ",ratings);
		return user;
	}

}
