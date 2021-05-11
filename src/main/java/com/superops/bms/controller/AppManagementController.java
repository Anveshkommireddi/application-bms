package com.superops.bms.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.superops.bms.model.AddUserRequest;
import com.superops.bms.model.CommonResponse;
import com.superops.bms.model.TokenResponse;
import com.superops.bms.model.UserRequest;
import com.superops.bms.service.UserServiceHandler;

@RestController
public class AppManagementController {
	
	private UserServiceHandler userServiceHandler;
	
	public AppManagementController(UserServiceHandler userServiceHandler) {
		this.userServiceHandler = userServiceHandler;
	}
	
	@PutMapping("/generateToken")
	public TokenResponse generateToken(@Valid @RequestBody UserRequest request) {
		return userServiceHandler.generateToken(request);
	}
	
	@PostMapping("/addUser")
	public CommonResponse addUser(@Valid @RequestBody AddUserRequest request) {
		return userServiceHandler.addUser(request);
	}
}
