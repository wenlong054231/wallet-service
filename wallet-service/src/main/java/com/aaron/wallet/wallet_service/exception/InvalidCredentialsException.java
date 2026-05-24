package com.aaron.wallet.wallet_service.exception;

public class InvalidCredentialsException extends BusinessException {
    public InvalidCredentialsException() {
        super("AUTH_001", "Invalid credentials");
    }
}