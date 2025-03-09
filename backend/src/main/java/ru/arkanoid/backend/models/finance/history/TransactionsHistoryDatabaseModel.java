package ru.arkanoid.backend.models.finance.history;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.UUID;

@Document("users")
@Builder
@Data
public class TransactionsHistoryDatabaseModel {
    @Id
    private UUID uuid;

    @Field("transactions")
    private List<TransactionHistoryNodeDatabaseModel> transactions;
}
