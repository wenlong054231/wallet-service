package com.aaron.wallet.wallet_service.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aaron.wallet.wallet_service.common.ApiResponse;
import com.aaron.wallet.wallet_service.model.dto.LoginRequestDTO;
import com.aaron.wallet.wallet_service.model.dto.LoginResponseDTO;
import com.aaron.wallet.wallet_service.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(@Valid @RequestBody LoginRequestDTO request) {

        return ResponseEntity.ok(ApiResponse.success("Login successful",authService.login(request)));
    }
}