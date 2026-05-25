package com.aaron.wallet.wallet_service.controller;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aaron.wallet.wallet_service.common.ApiResponse;
import com.aaron.wallet.wallet_service.model.dto.DepositRequestDTO;
import com.aaron.wallet.wallet_service.model.dto.TransferRequestDTO;
import com.aaron.wallet.wallet_service.model.dto.WithdrawRequestDTO;
import com.aaron.wallet.wallet_service.model.entity.Transaction;
import com.aaron.wallet.wallet_service.service.TransactionService;

import jakarta.validation.Valid;

@RequestMapping(path="/transaction")
@RestController
public class TransactionController {
	
	private final TransactionService transactionService;

	public TransactionController(TransactionService transactionService) {
		super();
		this.transactionService = transactionService;
	}
	
	@PostMapping("/deposit")
	public ResponseEntity<ApiResponse<BigDecimal>> deposit(@Valid @RequestBody DepositRequestDTO req) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();
	    return ResponseEntity.ok(transactionService.deposit(email, req.amount()));
	}
	
	@PostMapping("/withdraw")
	public ResponseEntity<ApiResponse<BigDecimal>> withdraw(@Valid @RequestBody WithdrawRequestDTO req) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();
	    return ResponseEntity.ok(transactionService.withdraw(email, req.amount()));
	}
	
	@PostMapping("/transfer")
	public ResponseEntity<ApiResponse<BigDecimal>> transfer(@Valid @RequestBody TransferRequestDTO req) {

	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    String fromEmail = authentication.getName();

	    return ResponseEntity.ok(transactionService.transfer(fromEmail, req.toEmail(), req.amount()));
	}
	
	@GetMapping("/history")
	public ResponseEntity<Page<Transaction>> history(@PageableDefault(size = 100) Pageable pageable) {

	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String email = authentication.getName();
	    return ResponseEntity.ok(transactionService.getHistory(email, pageable));
	}
}
