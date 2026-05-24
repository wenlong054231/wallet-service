package com.aaron.wallet.wallet_service.exception;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException() {
        super("USER_404", "User not found");
    }
}