package ru.arkanoid.backend.services;

import ru.arkanoid.backend.models.finance.WithdrawRequestCreationModel;
import ru.arkanoid.backend.user.User;

public interface CashierService {
    void deposit(User user, int amount);
    void withdraw(User user, WithdrawRequestCreationModel model);
    void exchangeCoins(User user);
}
