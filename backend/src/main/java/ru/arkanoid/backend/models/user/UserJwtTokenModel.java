package ru.arkanoid.backend.models.user;

import lombok.Builder;
import lombok.Data;
import ru.arkanoid.backend.user.Role;

import java.util.UUID;

@Data
@Builder
public class UserJwtTokenModel {
    private UUID uuid;
    private String email;
}
