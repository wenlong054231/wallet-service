package com.aaron.wallet.wallet_service.model.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public record DepositRequestDTO(@NotNull String email, @NotNull BigDecimal amount) {
	
}
