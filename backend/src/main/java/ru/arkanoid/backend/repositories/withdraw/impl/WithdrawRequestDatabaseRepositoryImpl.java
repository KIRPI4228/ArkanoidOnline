package ru.arkanoid.backend.repositories.withdraw.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.arkanoid.backend.database.WithdrawRequestDatabase;
import ru.arkanoid.backend.repositories.withdraw.WithdrawRequestRepository;
import ru.arkanoid.backend.serializers.finance.WithdrawRequestDatabaseSerializer;
import ru.arkanoid.backend.user.finance.WithdrawRequest;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class WithdrawRequestDatabaseRepositoryImpl implements WithdrawRequestRepository {
    private final WithdrawRequestDatabase database;
    private final WithdrawRequestDatabaseSerializer serializer;

    @Override
    public List<WithdrawRequest> getAllWithdrawRequests(UUID userUuid) {
        return database.findAllByUserUuid(userUuid).stream()
                .map(withdraw -> serializer.serialize(withdraw))
                .toList();
    }

    @Override
    public WithdrawRequest getWithdrawRequest(UUID uuid) {
        return serializer.serialize(database.findByUuid(uuid));
    }

    @Override
    public void save(WithdrawRequest withdrawRequest) {
        database.save(serializer.deserialize(withdrawRequest));
    }
}
