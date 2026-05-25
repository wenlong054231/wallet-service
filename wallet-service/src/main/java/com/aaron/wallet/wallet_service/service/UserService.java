package com.aaron.wallet.wallet_service.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.aaron.wallet.wallet_service.model.entity.User;
import com.aaron.wallet.wallet_service.model.entity.Wallet;
import com.aaron.wallet.wallet_service.repository.UserRepository;
import com.aaron.wallet.wallet_service.repository.WalletRepository;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, WalletRepository walletRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Transactional
    public void register(String email, String password) {

        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        User savedUser = userRepository.save(user);

        Wallet wallet = new Wallet();
        wallet.setUser(savedUser);
        wallet.setBalance(BigDecimal.ZERO);

        walletRepository.save(wallet);
    }
}
