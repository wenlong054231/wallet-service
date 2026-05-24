package com.aaron.wallet.wallet_service.controller;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aaron.wallet.wallet_service.common.ApiResponse;
import com.aaron.wallet.wallet_service.model.dto.DepositRequestDTO;
import com.aaron.wallet.wallet_service.model.dto.WithdrawRequestDTO;
import com.aaron.wallet.wallet_service.service.TransactionService;

@RequestMapping(path="/transaction")
@RestController
public class TransactionController {
	
	private final TransactionService transactionService;

	public TransactionController(TransactionService transactionService) {
		super();
		this.transactionService = transactionService;
	}
	
	@PostMapping("/deposit")
	public ResponseEntity<ApiResponse<BigDecimal>> deposit(@RequestBody DepositRequestDTO req) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();
	    return ResponseEntity.ok(transactionService.deposit(email, req.amount()));
	}
	
	@PostMapping("/withdraw")
	public ResponseEntity<ApiResponse<BigDecimal>> withdraw(@RequestBody WithdrawRequestDTO req) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();
	    return ResponseEntity.ok(transactionService.withdraw(email, req.amount()));
	}
}
