package com.aaron.wallet.wallet_service.exception;

public class WalletNotFoundException extends BusinessException{
    public WalletNotFoundException() {
        super("USER_404", "Wallet not found");
    }
}
