package ru.arkanoid.backend.models.user.rest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResetPasswordModel {
    private String email;
    private String newPassword;
}
