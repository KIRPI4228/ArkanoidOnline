package ru.arkanoid.backend.user.finance.history;

import ru.arkanoid.backend.helpers.TimeHelper;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public interface TransactionsHistory {
    UUID getUuid();

    List<TransactionHistoryNode> getAllTransactions();


    default List<TransactionHistoryNode> getTransactionsForDays(int days) {
        return getAllTransactions().stream()
                .filter(node -> TimeHelper.getCurrentMillis() < node.getDate().getTime() + TimeUnit.DAYS.toMillis(days))
                .toList();
    }

    default void addNode(TransactionHistoryNode node) {
        getAllTransactions().add(node);
    }
}
