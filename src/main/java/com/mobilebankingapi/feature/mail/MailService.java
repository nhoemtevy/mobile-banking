package com.mobilebankingapi.feature.mail;

import com.mobilebankingapi.feature.mail.dto.MailRequest;
import com.mobilebankingapi.feature.mail.dto.MailResponse;

public interface MailService {
    MailResponse send(MailRequest mailRequest);
}
