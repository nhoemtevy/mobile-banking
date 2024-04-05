package com.mobilebankingapi.feature.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AccountCreateRequest(
        @NotBlank(message = "Alias is required")
        String alias,

        @NotNull(message = "First balance is required (5$ up)")
        BigDecimal balance,

        @NotBlank(message = "Account type alias is required")
        String accountTypeAlias,

        @NotBlank(message = "User owner is required")
        String userUuid,

        String cardNumber

) {
}
