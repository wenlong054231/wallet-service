package com.aaron.wallet.wallet_service.model.dto;

import jakarta.validation.constraints.NotNull;

public record RegisterRequestDTO(@NotNull String email, @NotNull String password) {

}
