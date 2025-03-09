package ru.arkanoid.backend.user.finance;

import java.util.UUID;

public interface Wallet {
    UUID getUuid();

    Balance getCoinBalance();
    Balance getCashBalance();
}
