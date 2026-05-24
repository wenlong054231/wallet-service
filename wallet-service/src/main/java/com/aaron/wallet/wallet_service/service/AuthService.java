package com.aaron.wallet.wallet_service.service;

import org.springframework.stereotype.Service;

import com.aaron.wallet.wallet_service.exception.BusinessException;
import com.aaron.wallet.wallet_service.model.dto.LoginRequestDTO;
import com.aaron.wallet.wallet_service.model.dto.LoginResponseDTO;
import com.aaron.wallet.wallet_service.model.entity.User;
import com.aaron.wallet.wallet_service.repository.UserRepository;
import com.aaron.wallet.wallet_service.security.JwtUtil;

@Service
public class AuthService {

	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;
	
	public AuthService(UserRepository userRepository, JwtUtil jwtUtil) {
		this.userRepository = userRepository;
		this.jwtUtil = jwtUtil;
	}
	
	public LoginResponseDTO login(LoginRequestDTO request) {

	    User user = userRepository.findByEmail(request.email());

	    if (user == null || user.getPassword() == null) {
	        throw new BusinessException("AUTH_001", "Invalid credentials");
	    }

	    if (!user.getPassword().equals(request.password())) {

	        throw new BusinessException("AUTH_001", "Invalid credentials");
	    }
	    
	    String token = jwtUtil.generateToken(user.getId(), request.email());
	    
	    return new LoginResponseDTO(token);
	}
}
