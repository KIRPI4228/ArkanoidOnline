package ru.arkanoid.backend.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.arkanoid.backend.models.finance.WithdrawRequestCreationModel;
import ru.arkanoid.backend.repositories.user.UserRepository;
import ru.arkanoid.backend.services.CashierService;
import ru.arkanoid.backend.services.WithdrawRequestService;
import ru.arkanoid.backend.user.User;

@Service
@RequiredArgsConstructor
public class CashierServiceImpl implements CashierService { // TODO: make i18n keys
    private final UserRepository userRepository;
    private final WithdrawRequestService withdrawRequestService;

    @Override
    public void deposit(User user, int amount) {
        user.getWallet().getCashBalance().topUp(amount, "Deposit");
        // TODO: make deposit
        userRepository.save(user);
    }

    @Override
    public void withdraw(User user, WithdrawRequestCreationModel model) {
        user.getWallet().getCashBalance().deduct(model.getAmount(), "Withdraw");
        withdrawRequestService.create(user, model);
        // TODO: make email cancellation
        userRepository.save(user);
    }

    @Override
    public void exchangeCoins(User user) {
        int amount = user.getWallet().getCoinBalance().getBalance();
        user.getWallet().getCoinBalance().deduct(amount, "Exchange to coins");
        user.getWallet().getCashBalance().topUp(amount, "Exchange from coins");
        userRepository.save(user);
    }
}
