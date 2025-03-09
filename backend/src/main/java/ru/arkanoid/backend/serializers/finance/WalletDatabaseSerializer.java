package ru.arkanoid.backend.serializers.finance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.arkanoid.backend.models.finance.WalletDatabaseModel;
import ru.arkanoid.backend.serializers.finance.history.TransactionsHistoryDatabaseSerializer;
import ru.arkanoid.backend.user.finance.Balance;
import ru.arkanoid.backend.user.finance.Wallet;
import ru.arkanoid.backend.user.finance.history.TransactionsHistory;

@Component
@RequiredArgsConstructor
public class WalletDatabaseSerializer implements WalletSerializer<WalletDatabaseModel>, WalletDeserializer<WalletDatabaseModel> {
    private final TransactionsHistoryDatabaseSerializer transactionsHistorySerializer;

    @Override
    public Wallet serialize(WalletDatabaseModel model) {
        return new DatabaseWallet(model);
    }

    @Override
    public WalletDatabaseModel deserialize(Wallet object) {
        return WalletDatabaseModel.builder()
                .uuid(object.getUuid())
                .cashBalance(object.getCashBalance().getBalance())
                .coinBalance(object.getCoinBalance().getBalance())
                .cashHistory(transactionsHistorySerializer.deserialize(object.getCashBalance().getHistory()))
                .coinHistory(transactionsHistorySerializer.deserialize(object.getCoinBalance().getHistory()))
                .build();
    }

    private class DatabaseWallet extends WalletSerializer.SerializableWallet {
        public DatabaseWallet(WalletDatabaseModel model) {
            super(
                    model.getUuid(),
                    new DatabaseBalance(transactionsHistorySerializer.serialize(model.getCashHistory()),
                            model.getCashBalance()),
                    new DatabaseBalance(transactionsHistorySerializer.serialize(model.getCoinHistory()),
                            model.getCoinBalance())
            );
        }
    }

    private class DatabaseBalance extends Balance {
        public DatabaseBalance(TransactionsHistory history, int balance) {
            super(balance, history);
        }
    }
}
