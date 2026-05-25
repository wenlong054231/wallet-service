package com.aaron.wallet.wallet_service.model.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TransferRequestDTO(@NotBlank @Email String toEmail, @NotNull @Positive BigDecimal amount) {

}
