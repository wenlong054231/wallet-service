package com.aaron.wallet.wallet_service.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.aaron.wallet.wallet_service.enums.TransactionStatus;
import com.aaron.wallet.wallet_service.enums.TransactionType;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long fromUserId;
	
	@Nullable
	private Long toUserId;
	private BigDecimal amount;
	
	@Column(length = 30)
	@Enumerated(EnumType.STRING)
	private TransactionType type;

	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private TransactionStatus status;
	
	private LocalDateTime  createdAt;
	
	protected Transaction() {}

	public Transaction(Long toUserId, Long fromUserId, BigDecimal amount, TransactionType type,
			TransactionStatus status) {
		super();
		this.fromUserId = fromUserId;
		this.toUserId = toUserId;
		this.amount = amount;
		this.type = type;
		this.status = status;
		this.createdAt = LocalDateTime.now();
	}

	public static Transaction deposit(Long toUserId, BigDecimal amount) {
		return new Transaction(null, toUserId, amount, TransactionType.DEPOSIT, TransactionStatus.SUCCESS);
	}
	
	public static Transaction withdraw(Long toUserId, BigDecimal amount) {
		return new Transaction(null, toUserId, amount, TransactionType.WITHDRAW, TransactionStatus.SUCCESS);
	}
	
	public static Transaction transferOut(Long fromUserId, Long toUserId, BigDecimal amount) {
		return new Transaction(fromUserId, toUserId, amount, TransactionType.TRANSFER_OUT, TransactionStatus.SUCCESS);
	}

	public static Transaction transferIn(Long toUserId,Long fromUserId, BigDecimal amount) {
		return new Transaction(toUserId, fromUserId, amount, TransactionType.TRANSFER_IN, TransactionStatus.SUCCESS);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(Long fromUserId) {
		this.fromUserId = fromUserId;
	}
	public Long getToUserId() {
		return toUserId;
	}
	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public TransactionType getType() {
		return type;
	}
	public void setType(TransactionType type) {
		this.type = type;
	}
	public TransactionStatus getStatus() {
		return status;
	}
	public void setStatus(TransactionStatus status) {
		this.status = status;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
