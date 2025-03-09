package ru.arkanoid.backend.models.finance;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.arkanoid.backend.models.finance.history.TransactionsHistoryDatabaseModel;

import java.util.UUID;

@Document("users")
@Builder
@Data
public class WalletDatabaseModel {
    @Id
    private UUID uuid;

    @Field("coin_balance")
    private int coinBalance;
    @Field("cash_balance")
    private int cashBalance;

    @Field("coin_history")
    private TransactionsHistoryDatabaseModel coinHistory;
    @Field("cash_history")
    private TransactionsHistoryDatabaseModel cashHistory;
}
