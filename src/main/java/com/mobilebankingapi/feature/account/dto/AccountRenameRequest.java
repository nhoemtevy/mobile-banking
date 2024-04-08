package com.mobilebankingapi.feature.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AccountRenameRequest(
        @NotBlank(message = "New name is required")
        @Size(message = "Account must be less than or equal to 100 characters")
        String newName
) {
}
