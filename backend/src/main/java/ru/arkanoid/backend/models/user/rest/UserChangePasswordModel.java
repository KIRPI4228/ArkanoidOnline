package ru.arkanoid.backend.models.user.rest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserChangePasswordModel {
    private String oldPassword;
    private String newPassword;
}
