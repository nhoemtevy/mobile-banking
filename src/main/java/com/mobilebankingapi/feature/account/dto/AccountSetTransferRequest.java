package com.mobilebankingapi.feature.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record AccountSetTransferRequest(
        @NotBlank(message = "Set Transfer Account Limit")
        @Size
        BigDecimal transferLimit
) {
}
