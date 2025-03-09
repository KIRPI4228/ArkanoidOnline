package ru.arkanoid.backend.repositories.user;

import ru.arkanoid.backend.user.User;

import java.util.UUID;

public interface UserRepository {

    User findUserByUuid(UUID uuid);
    User findUserByEmail(String email);
    boolean containsUserByUuid(UUID uuid);
    boolean containsUserByEmail(String email);

    void save(User user);
}
