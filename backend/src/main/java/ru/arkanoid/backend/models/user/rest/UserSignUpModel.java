package ru.arkanoid.backend.models.user.rest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSignUpModel {
    private String username;
    private String email;
    private String password;
    private String referral;
}
