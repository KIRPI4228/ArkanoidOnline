package ru.arkanoid.backend.repositories.withdraw;

import ru.arkanoid.backend.user.finance.WithdrawRequest;

import java.util.List;
import java.util.UUID;

public interface WithdrawRequestRepository {
    List<WithdrawRequest> getAllWithdrawRequests(UUID userUuid);
    WithdrawRequest getWithdrawRequest(UUID uuid);

    void save(WithdrawRequest withdrawRequest);
}
