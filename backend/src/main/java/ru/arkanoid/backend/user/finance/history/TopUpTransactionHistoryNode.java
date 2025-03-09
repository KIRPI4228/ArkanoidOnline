package ru.arkanoid.backend.user.finance.history;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class TopUpTransactionHistoryNode implements TransactionHistoryNode {
    private UUID uuid = UUID.randomUUID();
    private Date date = Date.from(Instant.now()); // TODO: make via helper
    private final String description;
    private final int amount;
    private final int balance;
}
