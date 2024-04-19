package com.mobilebankingapi.feature.mail;

import com.mobilebankingapi.feature.mail.dto.MailRequest;
import com.mobilebankingapi.feature.mail.dto.MailResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RequiredArgsConstructor
@Service
@Slf4j
public class MailServiceImpl implements MailService{
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    @Override
    public MailResponse send(MailRequest mailRequest) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        Context context = new Context();
        context.setVariable("name", "ISTAD");

        String htmlTemple = templateEngine.process("mail/sample", context);

        try {
            mimeMessageHelper.setTo(mailRequest.to());
            mimeMessageHelper.setSubject(mailRequest.subject());
            mimeMessageHelper.setText(htmlTemple);
        } catch (MessagingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Something went wrong");
        }

        javaMailSender.send(mimeMessage);
        return new MailResponse("Mail sand successfully");
    }
}
