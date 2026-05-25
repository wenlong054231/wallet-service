package com.aaron.wallet.wallet_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import com.aaron.wallet.wallet_service.model.entity.Wallet;
import java.util.Optional;

import jakarta.persistence.LockModeType;

public interface WalletRepository extends JpaRepository<Wallet, Long>{

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Wallet> findByUserId(Long id);
}
