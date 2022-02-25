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

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @SneakyThrows
    @Async
    public void sendVerification(User user) {
        String template = "mail/verification_mail";
        String subject = "Activation code";
        sendMail(user, template, subject);
    }

    @SneakyThrows
    @Async
    public void sendLockInfo(User user) {
        String template = "mail/unlock_mail";
        String subject = "Account locked";
        sendMail(user, template, subject);
    }

    private void sendMail(User user, String template, String subject) throws MessagingException {
        Context context = new Context();
        context.setVariable("User", user);

        String proccess = templateEngine.process(template, context);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper =  new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setFrom("hello@eshop.com");
        helper.setTo(user.getEmail());
        helper.setSubject(subject);
        helper.setText(proccess, true);

        mailSender.send(mimeMessage);
    }
}
