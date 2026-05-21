package com.aaron.wallet.wallet_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aaron.wallet.wallet_service.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
