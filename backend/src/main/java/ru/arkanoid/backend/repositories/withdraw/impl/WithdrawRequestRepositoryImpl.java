package ru.arkanoid.backend.repositories.withdraw.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.arkanoid.backend.repositories.withdraw.WithdrawRequestRepositories;
import ru.arkanoid.backend.repositories.withdraw.WithdrawRequestRepository;
import ru.arkanoid.backend.user.finance.WithdrawRequest;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;

@Primary
@Repository
@RequiredArgsConstructor
public class WithdrawRequestRepositoryImpl implements WithdrawRequestRepository {
    private final WithdrawRequestRepositories repositories;
    private final WithdrawRequestDatabaseRepositoryImpl databaseRepository;

    @Override
    public List<WithdrawRequest> getAllWithdrawRequests(UUID userUuid) {
        return repositories.getValue(repository -> repository.getAllWithdrawRequests(userUuid));
    }

    @Override
    public WithdrawRequest getWithdrawRequest(UUID uuid) {
        return repositories.getValue(repository -> repository.getWithdrawRequest(uuid));
    }

    @Override
    public void save(WithdrawRequest withdrawRequest) {
        repositories.getRepository().save(withdrawRequest);
    }

    @PostConstruct
    public void init() {
        repositories.setMainRepository(databaseRepository);
        repositories.add(databaseRepository);
    }
}
