package ru.arkanoid.backend.serializers.finance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.arkanoid.backend.serializers.Serializer;
import ru.arkanoid.backend.user.finance.WithdrawRequest;
import ru.arkanoid.backend.user.finance.WithdrawStatus;
import ru.arkanoid.backend.user.finance.WithdrawType;

import java.util.UUID;

public interface WithdrawRequestSerializer<M> extends Serializer<WithdrawRequest, M> {

    @AllArgsConstructor
    @Getter
    class SerializableWithdrawRequest implements WithdrawRequest {
        private UUID uuid;
        private UUID userUuid;
        private WithdrawType type;
        private WithdrawStatus status;
        private String requisites;
        private int amount;
    }
}
