package com.aaron.wallet.wallet_service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aaron.wallet.wallet_service.model.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.fromUserId = :userId OR t.toUserId = :userId ORDER BY t.createdAt DESC")
    Page<Transaction> findByUserId(@Param("userId") Long userId, Pageable pageable);
}