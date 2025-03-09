package ru.arkanoid.backend.serializers.finance;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.arkanoid.backend.serializers.Serializer;
import ru.arkanoid.backend.user.finance.Balance;
import ru.arkanoid.backend.user.finance.Wallet;

import java.util.UUID;

public interface WalletSerializer<M> extends Serializer<Wallet, M> {
    @Data
    @AllArgsConstructor
    class SerializableWallet implements Wallet {
        private final UUID uuid;
        private final Balance cashBalance;
        private final Balance coinBalance;
    }
}
