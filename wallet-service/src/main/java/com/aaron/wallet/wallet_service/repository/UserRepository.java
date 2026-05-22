package com.aaron.wallet.wallet_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aaron.wallet.wallet_service.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmail(String email);

	User findByEmail(String email);

}
