package com.aaron.wallet.wallet_service.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aaron.wallet.wallet_service.user.dto.RegisterRequestDTO;
import com.aaron.wallet.wallet_service.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;		
	}
	
	@PostMapping(path="/register")
	public void registerUser(@RequestBody RegisterRequestDTO request) {
		userService.register(request.email(), request.password());
	}
}
