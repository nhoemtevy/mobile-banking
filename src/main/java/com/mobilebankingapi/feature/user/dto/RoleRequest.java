package com.mobilebankingapi.feature.user.dto;

import jakarta.validation.constraints.NotBlank;

public record RoleRequest(
        @NotBlank
        String name
) {
}
