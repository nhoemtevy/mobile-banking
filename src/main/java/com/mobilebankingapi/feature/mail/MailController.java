package com.mobilebankingapi.feature.mail;

import com.mobilebankingapi.feature.mail.dto.MailRequest;
import com.mobilebankingapi.feature.mail.dto.MailResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping
    MailResponse send(@Valid @RequestBody MailRequest mailRequest){
        return mailService.send(mailRequest);
    }
}
