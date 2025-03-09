package ru.arkanoid.backend.models.user.rest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserJwtAuthenticationResponseModel {
    private String token;
}
