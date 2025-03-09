package ru.arkanoid.backend.services;

import org.springframework.mail.SimpleMailMessage;

public interface MailService {
    void sendTextLetter(String to, String subject, String text);
    void sendSignUpVerificationLetter(String to, String key);
    void sendResetPasswordVerificationLetter(String to, String key);
    void sendLetter(SimpleMailMessage message);
}
