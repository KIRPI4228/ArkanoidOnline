package ru.arkanoid.backend.serializers.finance.history;

import org.springframework.stereotype.Component;
import ru.arkanoid.backend.models.finance.history.TransactionHistoryNodeDatabaseModel;
import ru.arkanoid.backend.user.finance.history.TransactionHistoryNode;

@Component
public class TransactionHistoryNodeDatabaseSerializer implements TransactionHistoryNodeSerializer<TransactionHistoryNodeDatabaseModel>,
        TransactionHistoryNodeDeserializer<TransactionHistoryNodeDatabaseModel> {
    @Override
    public TransactionHistoryNode serialize(TransactionHistoryNodeDatabaseModel model) {
        return new DatabaseTransactionHistoryNode(model);
    }

    @Override
    public TransactionHistoryNodeDatabaseModel deserialize(TransactionHistoryNode object) {
        return TransactionHistoryNodeDatabaseModel.builder()
                .uuid(object.getUuid())
                .description(object.getDescription())
                .date(object.getDate())
                .amount(object.getAmount())
                .balance(object.getBalance())
                .build();
    }

    private class DatabaseTransactionHistoryNode extends TransactionHistoryNodeSerializer.SerializableTransactionHistoryNode {

        public DatabaseTransactionHistoryNode(TransactionHistoryNodeDatabaseModel model) {
            super(model.getUuid(), model.getDate(), model.getDescription(), model.getAmount(), model.getBalance());
        }
    }
}
