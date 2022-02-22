package com.example.eshop.service;

import com.example.eshop.model.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @SneakyThrows
    @Async
    public void sendVerification(User user) {
        Context context = new Context();
        context.setVariable("User", user);

        String proccess = templateEngine.process("mail/verification_mail", context);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper =  new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setFrom("hello@eshop.com");
        helper.setTo(user.getEmail());
        helper.setSubject("Activation code");
        helper.setText(proccess, true);

        mailSender.send(mimeMessage);
    }

    @SneakyThrows
    @Async
    public void sendUnlockUrl(User user) {
        Context context = new Context();
        context.setVariable("User", user);

        String proccess = templateEngine.process("mail/verification_mail", context);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper =  new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setFrom("hello@eshop.com");
        helper.setTo(user.getEmail());
        helper.setSubject("Activation code");
        helper.setText(proccess, true);

        mailSender.send(mimeMessage);
    }
}
