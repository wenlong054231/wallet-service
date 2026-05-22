package com.aaron.wallet.wallet_service.user.dto;

import jakarta.validation.constraints.NotNull;

public record RegisterRequestDTO(@NotNull String email, @NotNull String password) {

}
