package ru.arkanoid.backend.models.user;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserResetPasswordVerificationJwtTokenModel {
    private UUID uuid;
    private String email;
    private String referral;
    private String password;
}
