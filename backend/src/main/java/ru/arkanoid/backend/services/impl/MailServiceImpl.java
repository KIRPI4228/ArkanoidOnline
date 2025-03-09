package ru.arkanoid.backend.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.arkanoid.backend.services.MailService;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    @Value("${frontend.host}")
    private String frontendHost;
    @Value("${frontend.port}")
    private int frontendPort;
    @Value("${frontend.secured}")
    private boolean frontendIsSecured;

    @Value("${spring.mail.username}")
    private String from;

    private String frontendLink;

    private final MailSender sender;

    @Override
    @Async
    public void sendTextLetter(String to, String subject, String text) {
        var message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        sendLetter(message);
    }

    @Override
    @Async
    public void sendSignUpVerificationLetter(String to, String key) {
        sendTextLetter(to, "Verification letter", "Your verification account link - " + frontendLink + "/sign-up/verify?key=" + key);
    }

    @Override
    @Async
    public void sendResetPasswordVerificationLetter(String to, String key) {
        sendTextLetter(to, "Verification letter", "Your reset password link - " + frontendLink + "/reset-password/verify?key=" + key);
    }

    @Override
    @Async
    public void sendLetter(SimpleMailMessage message) {
        sender.send(message);
    }

    @PostConstruct
    public void init() {
        frontendLink = generateFrontendLink();
    }

    private String generateFrontendLink() {
        return "http" + (frontendIsSecured ? "s" : "") + "://" + frontendHost + (frontendPort == 80 ? "" : ":" + frontendPort);
    }
}
