package com.mobilebankingapi.feature.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordRequest(
    @NotBlank(message = "Password is required")
    String oldPassword,
    @NotBlank(message = "Password is required")
    String newPassword,
    @NotBlank(message = "confirmedPassword is required")
    String confirmedPassword
) {
}
