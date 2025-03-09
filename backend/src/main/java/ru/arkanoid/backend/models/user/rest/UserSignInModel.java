package ru.arkanoid.backend.models.user.rest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSignInModel {
    private String email;
    private String password;
}
