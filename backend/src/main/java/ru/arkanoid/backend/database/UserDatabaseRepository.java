package ru.arkanoid.backend.database;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.arkanoid.backend.models.user.UserDatabaseModel;

import java.util.Optional;
import java.util.UUID;


public interface UserDatabaseRepository extends MongoRepository<UserDatabaseModel, UUID> {
    Optional<UserDatabaseModel> findByUuid(UUID uuid);
    Optional<UserDatabaseModel> findByEmail(String email);
}
