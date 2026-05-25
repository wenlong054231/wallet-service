package com.aaron.wallet.wallet_service.service;

import org.springframework.stereotype.Service;

import com.aaron.wallet.wallet_service.exception.BusinessException;
import com.aaron.wallet.wallet_service.model.dto.LoginRequestDTO;
import com.aaron.wallet.wallet_service.model.dto.LoginResponseDTO;
import com.aaron.wallet.wallet_service.model.entity.User;
import com.aaron.wallet.wallet_service.repository.UserRepository;
import com.aaron.wallet.wallet_service.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AuthService {

	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;
	private final PasswordEncoder passwordEncoder;

	public AuthService(UserRepository userRepository, JwtUtil jwtUtil,PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.jwtUtil = jwtUtil;
		this.passwordEncoder = passwordEncoder;
	}
	
	public LoginResponseDTO login(LoginRequestDTO request) {

	    User user = userRepository.findByEmail(request.email());

	    if (user == null || user.getPassword() == null) {
	        throw new BusinessException("AUTH_001", "Invalid credentials");
	    }

	    if (!passwordEncoder.matches(request.password(), user.getPassword())) {

	        throw new BusinessException("AUTH_001", "Invalid credentials");
	    }
	    
	    String token = jwtUtil.generateToken(user.getId(), request.email());
	    
	    return new LoginResponseDTO(token);
	}
}
