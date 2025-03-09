package ru.arkanoid.backend.models.user;

import lombok.Builder;
import lombok.Data;
import ru.arkanoid.backend.user.Role;

import java.util.UUID;

@Data
@Builder
public class UserVerificationJwtTokenModel {
    private UUID uuid;
    private String username;
    private String email;
    private String password;
    private String referral;
    private Role role;
}
