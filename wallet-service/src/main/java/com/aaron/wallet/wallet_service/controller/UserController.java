package com.aaron.wallet.wallet_service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.aaron.wallet.wallet_service.common.ApiResponse;
import com.aaron.wallet.wallet_service.model.dto.RegisterRequestDTO;
import com.aaron.wallet.wallet_service.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;		
	}
	
	@PostMapping(path="/register")
	public ResponseEntity<ApiResponse<Void>> registerUser(@Valid @RequestBody RegisterRequestDTO request) {
	    userService.register(request.email(), request.password());
	    return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("User registered successfully", null));
	}
}
