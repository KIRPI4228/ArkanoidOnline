package ru.arkanoid.backend;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import ru.arkanoid.backend.models.user.UserJwtTokenModel;
import ru.arkanoid.backend.models.user.UserResetPasswordVerificationJwtTokenModel;
import ru.arkanoid.backend.models.user.UserVerificationJwtTokenModel;
import ru.arkanoid.backend.services.JwtService;
import ru.arkanoid.backend.user.Role;

import java.util.UUID;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
public class JwtTests {
    private static final Pattern JWT_TOKEN_REGEX_PATTERN = Pattern.compile("(^[A-Za-z0-9-_]*\\.[A-Za-z0-9-_]*\\.[A-Za-z0-9-_]*$)");


    private static final UUID USER_UUID_EXAMPLE = UUID.randomUUID();
    private static final Role USER_ROLE_EXAMPLE = Role.USER;
    private static final String USER_EMAIL_EXAMPLE = "example@mail.com";
    private static final String USER_USERNAME_EXAMPLE = "user";
    private static final String USER_PASSWORD_EXAMPLE = "Example_password123";
    private static final String USER_REFERRAL_EXAMPLE = "";

    private static final UserJwtTokenModel USER_JWT_TOKEN_MODEL_EXAMPLE = UserJwtTokenModel.builder()
            .uuid(USER_UUID_EXAMPLE)
            .email(USER_EMAIL_EXAMPLE)
            .build();

    private static final UserVerificationJwtTokenModel USER_VERIFICATION_JWT_TOKEN_MODEL_EXAMPLE = UserVerificationJwtTokenModel.builder()
            .uuid(USER_UUID_EXAMPLE)
            .email(USER_EMAIL_EXAMPLE)
            .username(USER_USERNAME_EXAMPLE)
            .password(USER_PASSWORD_EXAMPLE)
            .referral(USER_REFERRAL_EXAMPLE)
            .role(USER_ROLE_EXAMPLE)
            .build();

    private static final UserResetPasswordVerificationJwtTokenModel USER_RESET_PASSWORD_VERIFICATION_JWT_TOKEN_MODEL_EXAMPLE = UserResetPasswordVerificationJwtTokenModel.builder()
            .uuid(USER_UUID_EXAMPLE)
            .email(USER_EMAIL_EXAMPLE)
            .password(USER_PASSWORD_EXAMPLE)
            .referral(USER_REFERRAL_EXAMPLE)
            .build();

    @Autowired
    private JwtService service;

    //@Test
    void generateTokenShouldReturnCorrectJwtToken() {
        assertTrue(JWT_TOKEN_REGEX_PATTERN.matcher(service.generateToken(USER_JWT_TOKEN_MODEL_EXAMPLE)).matches());
        assertTrue(JWT_TOKEN_REGEX_PATTERN.matcher(service.generateToken(USER_VERIFICATION_JWT_TOKEN_MODEL_EXAMPLE)).matches());
        assertTrue(JWT_TOKEN_REGEX_PATTERN.matcher(service.generateToken(USER_RESET_PASSWORD_VERIFICATION_JWT_TOKEN_MODEL_EXAMPLE)).matches());
    }
}
