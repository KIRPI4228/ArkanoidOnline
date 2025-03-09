package ru.arkanoid.backend.user.finance.history;

import java.util.Date;
import java.util.UUID;

public interface TransactionHistoryNode {
    UUID getUuid();
    Date getDate();
    String getDescription();
    int getAmount();
    int getBalance();
}
