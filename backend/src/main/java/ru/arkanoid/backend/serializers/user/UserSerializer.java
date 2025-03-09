package ru.arkanoid.backend.serializers.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.arkanoid.backend.serializers.Serializer;
import ru.arkanoid.backend.user.Role;
import ru.arkanoid.backend.user.User;
import ru.arkanoid.backend.user.finance.Wallet;

import java.util.UUID;

public interface UserSerializer<M> extends Serializer<User, M> {
    @Data
    @AllArgsConstructor
    class SerializableUser implements User {
        private final UUID uuid;
        private Role role;
        private String username;
        private final String email;
        private String password;
        private final String referral;
        private Wallet wallet;
    }
}
