package com.aaron.wallet.wallet_service.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aaron.wallet.wallet_service.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmail(String email);

}
