package com.micro.user.service.UserService.service;

import java.util.List;

import com.micro.user.service.UserService.entities.User;

public interface UserService {

	User saveUser(User user);
	
	List<User> getAllUser ();
	
	User getUser (String userId);
}
