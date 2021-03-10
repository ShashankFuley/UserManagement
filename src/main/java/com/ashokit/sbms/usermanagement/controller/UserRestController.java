package com.ashokit.sbms.usermanagement.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.sbms.usermanagement.entity.User;
import com.ashokit.sbms.usermanagement.service.UserServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/user")
@Api(value = "UserRestController")
public class UserRestController {

	private UserServiceImpl userService;
	
	UserRestController(UserServiceImpl userService){
		this.userService = userService;
	}
	
	//Method to get countries
	@ApiOperation(value = "To get countries")
	@GetMapping("/getCountries")
	public ResponseEntity<Map<Long,String>> getCountries(){
		Map<Long , String> map = this.userService.getCountries();
		return ResponseEntity.ok(map);
	}
	
	//Method to get states based on country id
	@ApiOperation(value = "To get states based on country id")
	@GetMapping("/getStates/{countryId}")
	public ResponseEntity<Map<Long,String>> getStates(@PathVariable final Long countryId){
		Map<Long , String> map = this.userService.getStates(countryId);
		return ResponseEntity.ok(map);
	}
	
	//Method to get cities based on state id
	@ApiOperation(value = "To get cities based on state id")
	@GetMapping("/getCities/{stateId}")
	public ResponseEntity<Map<Long,String>> getCities(@PathVariable final Long stateId){
		Map<Long , String> map = this.userService.getCities(stateId);
		return ResponseEntity.ok(map);
	}
	
	@PostMapping
	public ResponseEntity<String> registerUser(User user){
		return ResponseEntity.status(201).body("Please check your email to unlock account");
	}
	
	//Method to get cities based on state id
	@ApiOperation(value = "To check if email is registerd")
	@GetMapping("isEmailRegistered/{email}")
	public ResponseEntity<Boolean> isValidEmail(@PathVariable final String email){
		Boolean isRegistered = this.userService.isEmailRegistered(email);
		return ResponseEntity.ok(isRegistered);
	}
}
