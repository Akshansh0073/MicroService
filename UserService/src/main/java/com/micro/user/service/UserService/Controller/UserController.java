package com.micro.user.service.UserService.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.user.service.UserService.entities.User;
import com.micro.user.service.UserService.service.UserService;
import com.micro.user.service.UserService.service.impl.UserServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	
	@PostMapping
	public ResponseEntity<User> createUser (@RequestBody User user){
		
		User saveUser = userService.saveUser(user);
		return new ResponseEntity<User>(saveUser,HttpStatus.CREATED);
	}
	
	@GetMapping("/{userId}")
	@CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallBack")
	public ResponseEntity<User> getSingleUser (@PathVariable String userId){
		
		User user = userService.getUser(userId);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	// creating fallback method for circuitbreaker
	public ResponseEntity<User> ratingHotelFallBack(String userId, Exception ex) {
		logger.info("Fallback is executed because service is down", ex.getMessage());
		User user = User.builder().email("dummy@email.com").name("dummy")
				.about("This user is created dummy because some service is down").userId("14255363").build();
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
		List<User> allUser = userService.getAllUser();
		return new ResponseEntity<List<User>>(allUser,HttpStatus.OK);
	}
	
}
