package com.aaron.wallet.wallet_service.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.aaron.wallet.wallet_service.common.ApiResponse;
import com.aaron.wallet.wallet_service.exception.BusinessException;
import com.aaron.wallet.wallet_service.exception.InsufficientBalanceException;
import com.aaron.wallet.wallet_service.exception.UserNotFoundException;
import com.aaron.wallet.wallet_service.model.entity.Transaction;
import com.aaron.wallet.wallet_service.model.entity.User;
import com.aaron.wallet.wallet_service.model.entity.Wallet;
import com.aaron.wallet.wallet_service.repository.TransactionRepository;
import com.aaron.wallet.wallet_service.repository.UserRepository;
import com.aaron.wallet.wallet_service.repository.WalletRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {

	private final TransactionRepository transactionRepository;
	private final UserRepository userRepository;
	private final WalletRepository walletRepository;
	
	public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository, WalletRepository walletRepository) {
		this.transactionRepository = transactionRepository;	
		this.userRepository = userRepository;
		this.walletRepository = walletRepository;
	}
	
	@Transactional
	public ApiResponse<BigDecimal> deposit(String email, BigDecimal amount) {

	    if (amount.compareTo(BigDecimal.ZERO) <= 0) {
	        throw new BusinessException("INVALID_AMOUNT", "Amount must be > 0");
	    }

	    User user = userRepository.findByEmail(email);
	    if (user == null) {
	    	throw new UserNotFoundException();
	    }

	    Wallet wallet = walletRepository.findByUserId(user.getId());
	    if (wallet == null) {
	        throw new RuntimeException("Wallet not found");
	    }

	    wallet.addBalance(amount);

	    walletRepository.save(wallet);
	    
	    Transaction transaction = Transaction.deposit(user.getId(), amount);
	    
	    transactionRepository.save(transaction);
	    
	    return ApiResponse.success("Deposit successful", wallet.getBalance());

	}
	
	@Transactional
	public ApiResponse<BigDecimal> withdraw(String email, BigDecimal amount) {

	    if (amount.compareTo(BigDecimal.ZERO) <= 0) {
	        throw new BusinessException("INVALID_AMOUNT", "Amount must be > 0");
	    }

	    User user = userRepository.findByEmail(email);
	    if (user == null) throw new UserNotFoundException();

	    Wallet wallet = walletRepository.findByUserId(user.getId());

	    if (wallet.getBalance().compareTo(amount) < 0) {
	        throw new InsufficientBalanceException();
	    }

	    wallet.deductBalance(amount);

	    Transaction transaction = Transaction.withdraw(user.getId(), amount);

	    walletRepository.save(wallet);
	    transactionRepository.save(transaction);

	    return ApiResponse.success("Withdraw successful", wallet.getBalance());
	}
}
