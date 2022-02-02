package com.example.eshop.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;

    @Async
    @SneakyThrows
    public void send(String email, String subject, String message) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper =  new MimeMessageHelper(mimeMessage, "UTF-8");

        helper.setFrom("hello@eshop.com");
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(message, true);

        mailSender.send(mimeMessage);
    }

}
