package ru.arkanoid.backend.serializers.finance.history;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.arkanoid.backend.serializers.Serializer;
import ru.arkanoid.backend.user.finance.history.TransactionHistoryNode;

import java.util.Date;
import java.util.UUID;

public interface TransactionHistoryNodeSerializer<M> extends Serializer<TransactionHistoryNode, M> {

    @Data
    @AllArgsConstructor
    class SerializableTransactionHistoryNode implements TransactionHistoryNode {
        private UUID uuid;
        private Date date;
        private String description;
        private int amount;
        private int balance;
    }
}
