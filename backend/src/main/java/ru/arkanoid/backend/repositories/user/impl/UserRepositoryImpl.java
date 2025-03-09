package ru.arkanoid.backend.repositories.user.impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.arkanoid.backend.repositories.user.UserRepositories;
import ru.arkanoid.backend.repositories.user.UserRepository;
import ru.arkanoid.backend.user.User;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Primary
@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserRepositories repositories;
    private final UserRepositoryDatabaseImpl userRepositoryDatabase;

    @Override
    public User findUserByUuid(UUID uuid) {
        return repositories.getValue(repository -> repository.findUserByUuid(uuid));
    }

    @Override
    public User findUserByEmail(String email) {
        return repositories.getValue(repository -> repository.findUserByEmail(email));
    }

    @Override
    public boolean containsUserByUuid(UUID uuid) {
        return repositories.getValue(repository -> repository.containsUserByUuid(uuid));
    }

    @Override
    public boolean containsUserByEmail(String email) {
        return repositories.getValue(repository -> repository.containsUserByEmail(email));
    }

    @Override
    public void save(User user) {
        repositories.getRepository().save(user);
    }

    @PostConstruct
    public void init() {
        repositories.setMainRepository(userRepositoryDatabase);
        repositories.add(userRepositoryDatabase);
    }
}
