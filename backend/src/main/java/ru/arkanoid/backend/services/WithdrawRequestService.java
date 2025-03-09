package ru.arkanoid.backend.services;

import ru.arkanoid.backend.models.finance.WithdrawRequestCreationModel;
import ru.arkanoid.backend.user.User;
import ru.arkanoid.backend.user.finance.WithdrawRequest;

import java.util.List;
import java.util.UUID;

public interface WithdrawRequestService {
    void accept(UUID uuid);
    void decline(UUID uuid);
    void create(User user, WithdrawRequestCreationModel model);

    List<WithdrawRequest> getWithdrawRequests(User user);
}
