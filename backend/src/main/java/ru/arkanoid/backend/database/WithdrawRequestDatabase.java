package ru.arkanoid.backend.database;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.arkanoid.backend.models.finance.WithdrawRequestDatabaseModel;

import java.util.List;
import java.util.UUID;

public interface WithdrawRequestDatabase extends MongoRepository<WithdrawRequestDatabaseModel, UUID> {
    WithdrawRequestDatabaseModel findByUuid(UUID uuid);
    List<WithdrawRequestDatabaseModel> findAllByUserUuid(UUID userUuid);
}
