package com.aaron.wallet.wallet_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aaron.wallet.wallet_service.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
