package ru.arkanoid.backend.user.finance.history;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class EmptyTransactionHistory implements TransactionsHistory {
    private UUID uuid = UUID.randomUUID();
    private List<TransactionHistoryNode> transactionHistoryNodes = new ArrayList<>();

    @Override
    public List<TransactionHistoryNode> getAllTransactions() {
        return transactionHistoryNodes;
    }
}
