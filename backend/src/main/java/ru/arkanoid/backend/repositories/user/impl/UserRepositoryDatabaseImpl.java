package ru.arkanoid.backend.repositories.user.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.arkanoid.backend.database.UserDatabaseRepository;
import ru.arkanoid.backend.repositories.user.UserRepository;
import ru.arkanoid.backend.user.User;
import ru.arkanoid.backend.serializers.user.UserDatabaseSerializer;

import java.util.UUID;

@Repository
@AllArgsConstructor
public class UserRepositoryDatabaseImpl implements UserRepository {

    private final UserDatabaseRepository database;
    private final UserDatabaseSerializer serializer;

    @Override
    public User findUserByUuid(UUID uuid) {
        return serializer.serialize(database.findByUuid(uuid)
                .orElse(null));
                //.orElseThrow(() -> new RuntimeException("Could not find user in database with uuid - " + uuid.toString())));
    }

    @Override
    public User findUserByEmail(String email) {
        return serializer.serialize(database.findByEmail(email)
                .orElse(null));
                //.orElseThrow(() -> new RuntimeException("Could not find user in database with email - " + email)));
    }

    @Override
    public boolean containsUserByUuid(UUID uuid) {
        return database.findByUuid(uuid).orElse(null) != null;
    }

    @Override
    public boolean containsUserByEmail(String email) {
        return database.findByEmail(email).orElse(null) != null;
    }

    @Override
    public void save(User user) {
        database.save(serializer.deserialize(user));
    }
}
