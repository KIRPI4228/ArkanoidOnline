package ru.arkanoid.backend.serializers.finance;

import org.springframework.stereotype.Component;
import ru.arkanoid.backend.models.finance.WithdrawRequestCreationModel;
import ru.arkanoid.backend.user.finance.WithdrawRequest;
import ru.arkanoid.backend.user.finance.WithdrawStatus;
import ru.arkanoid.backend.user.finance.WithdrawType;

import java.util.UUID;

@Component
public class WithdrawRequestCreationSerializer implements WithdrawRequestSerializer<WithdrawRequestCreationModel> {
    private UUID userUuid;

    @Override
    public WithdrawRequest serialize(WithdrawRequestCreationModel model) {
        return new CreatedWithdrawRequest(model);
    }
    public WithdrawRequestCreationSerializer userUuid(UUID userUuid) {
        var serializer = new WithdrawRequestCreationSerializer();
        serializer.userUuid = userUuid;
        return serializer;
    }

    private class CreatedWithdrawRequest extends SerializableWithdrawRequest {
        public CreatedWithdrawRequest(WithdrawRequestCreationModel model) {
            super(
                    UUID.randomUUID(),
                    userUuid,
                    WithdrawType.valueOf(model.getType()),
                    WithdrawStatus.PENDING,
                    model.getRequisites(),
                    model.getAmount()
            );
        }
    }
}
