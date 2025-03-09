package ru.arkanoid.backend.serializers.finance.history;

import ru.arkanoid.backend.serializers.Deserializer;
import ru.arkanoid.backend.user.finance.history.TransactionHistoryNode;

public interface TransactionHistoryNodeDeserializer<M> extends Deserializer<TransactionHistoryNode, M> {
}
