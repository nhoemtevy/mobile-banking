package com.mobilebankingapi.feature.user.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UserCreateRequst(
        @NotNull
        @Max(9999)
        @Positive
        Integer pin,

        @NotBlank
        @Size(max = 20)
        String phoneNumber,

        @NotBlank
        String password,

        @NotBlank
        String comfirmedPassword,

        @NotBlank
        @Size(max = 40)
        String name,

        @NotBlank
        @Size(max = 20)
        String nationalCardId,

        @NotBlank
        @Size(max = 6)
        String gender,

        @NotBlank
        @Size(max = 20)
        String studentId,

        @NotNull
        LocalDate dob
) {
}
