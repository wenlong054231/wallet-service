package com.aaron.wallet.wallet_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aaron.wallet.wallet_service.wallet.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long>{

}
