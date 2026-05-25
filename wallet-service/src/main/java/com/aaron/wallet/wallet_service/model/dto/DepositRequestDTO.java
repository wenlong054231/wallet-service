package com.aaron.wallet.wallet_service.model.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DepositRequestDTO(@NotNull @Positive BigDecimal amount) {
	
}
