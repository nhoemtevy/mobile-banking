package com.mobilebankingapi.feature.mail.dto;

import jakarta.validation.constraints.NotBlank;

public record MailRequest(

        @NotBlank(message = "to is require")
        String to,
        @NotBlank(message = "subject is require")
        String subject
//        @NotBlank(message = "body is require")
//        String body
) {
}
