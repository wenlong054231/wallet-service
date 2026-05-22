package com.aaron.wallet.wallet_service.user.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.aaron.wallet.wallet_service.repository.WalletRepository;
import com.aaron.wallet.wallet_service.user.entity.User;
import com.aaron.wallet.wallet_service.user.repository.UserRepository;
import com.aaron.wallet.wallet_service.wallet.Wallet;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    public UserService(UserRepository userRepository,
                        WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }
    
    @Transactional
    public void register(String email, String password) {

        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        User savedUser = userRepository.save(user);

        Wallet wallet = new Wallet();
        wallet.setUser(savedUser);
        wallet.setBalance(BigDecimal.ZERO);

        walletRepository.save(wallet);
    }
}
