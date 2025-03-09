package ru.arkanoid.backend.serializers.finance.history;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.arkanoid.backend.serializers.Serializer;
import ru.arkanoid.backend.user.finance.history.TransactionHistoryNode;
import ru.arkanoid.backend.user.finance.history.TransactionsHistory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface TransactionsHistorySerializer<M> extends Serializer<TransactionsHistory, M> {
    @Data
    @AllArgsConstructor
    class SerializableTransactionsHistory implements TransactionsHistory {
        private UUID uuid;
        private List<TransactionHistoryNode> transactionHistoryNodes;
        /*protected SerializableTransactionsHistory(UUID uuid, List<TransactionHistoryNode> transactionHistoryNodes) {
            this.uuid = uuid;
            this.transactionHistoryNodes = transactionHistoryNodes;

            if (this.transactionHistoryNodes == null) {
                this.transactionHistoryNodes = new ArrayList<>();
            }
        }*/

        @Override
        public List<TransactionHistoryNode> getAllTransactions() {
            return transactionHistoryNodes;
        }
    }
}
