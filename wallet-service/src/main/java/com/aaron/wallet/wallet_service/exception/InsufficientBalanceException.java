package com.aaron.wallet.wallet_service.exception;

public class InsufficientBalanceException extends BusinessException {
    public InsufficientBalanceException() {
        super("WALLET_001", "Insufficient balance");
    }
}