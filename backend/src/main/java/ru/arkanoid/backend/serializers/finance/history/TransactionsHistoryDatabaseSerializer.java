package ru.arkanoid.backend.serializers.finance.history;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.arkanoid.backend.models.finance.history.TransactionsHistoryDatabaseModel;
import ru.arkanoid.backend.user.finance.history.TransactionsHistory;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TransactionsHistoryDatabaseSerializer implements TransactionsHistorySerializer<TransactionsHistoryDatabaseModel>,
        TransactionsHistoryDeserializer<TransactionsHistoryDatabaseModel> {
    private final TransactionHistoryNodeDatabaseSerializer transactionHistoryNodeSerializer;

    @Override
    public TransactionsHistory serialize(TransactionsHistoryDatabaseModel model) {
        return new DatabaseTransactionHistory(model);
    }

    @Override
    public TransactionsHistoryDatabaseModel deserialize(TransactionsHistory object) {
        return TransactionsHistoryDatabaseModel.builder()
                .uuid(object.getUuid())
                .transactions(object.getAllTransactions().stream()
                        .map(node -> transactionHistoryNodeSerializer.deserialize(node))
                        .toList())
                .build();
    }

    private class DatabaseTransactionHistory extends TransactionsHistorySerializer.SerializableTransactionsHistory {

        public DatabaseTransactionHistory(TransactionsHistoryDatabaseModel model) {
            super(model.getUuid(), model.getTransactions().stream()
                    .map(node -> transactionHistoryNodeSerializer.serialize(node))
                    .collect(Collectors.toList()));
        }
    }
}
