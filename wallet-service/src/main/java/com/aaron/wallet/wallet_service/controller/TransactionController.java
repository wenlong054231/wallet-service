package com.aaron.wallet.wallet_service.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aaron.wallet.wallet_service.model.dto.DepositRequestDTO;
import com.aaron.wallet.wallet_service.repository.TransactionRepository;
import com.aaron.wallet.wallet_service.service.TransactionService;

@RequestMapping(path="/transaction")
@RestController
public class TransactionController {

	private final TransactionService transactionService;

	public TransactionController(TransactionService transactionService) {
		super();
		this.transactionService = transactionService;
	}
	
	@PostMapping(path="/deposit")
	public void deposit(@RequestBody DepositRequestDTO request) {
		
		transactionService.deposit(request.email(), request.amount());
	}
}
