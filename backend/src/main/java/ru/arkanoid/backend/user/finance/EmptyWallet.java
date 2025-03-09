package ru.arkanoid.backend.user.finance;

import lombok.Getter;

import java.util.UUID;

@Getter
public class EmptyWallet implements Wallet {
    private UUID uuid = UUID.randomUUID();
    private Balance coinBalance = new EmptyBalance();
    private Balance cashBalance = new EmptyBalance();
}
