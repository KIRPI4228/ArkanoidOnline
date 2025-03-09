package ru.arkanoid.backend.serializers.finance;

import org.springframework.stereotype.Component;
import ru.arkanoid.backend.models.finance.WithdrawRequestDatabaseModel;
import ru.arkanoid.backend.user.finance.WithdrawRequest;
import ru.arkanoid.backend.user.finance.WithdrawStatus;
import ru.arkanoid.backend.user.finance.WithdrawType;

@Component
public class WithdrawRequestDatabaseSerializer implements WithdrawRequestSerializer<WithdrawRequestDatabaseModel>,
        WithdrawRequestDeserializer<WithdrawRequestDatabaseModel> {
    @Override
    public WithdrawRequestDatabaseModel deserialize(WithdrawRequest object) {
        return WithdrawRequestDatabaseModel.builder()
                .requisites(object.getRequisites())
                .status(object.getStatus().name())
                .type(object.getType().name())
                .amount(object.getAmount())
                .userUuid(object.getUserUuid())
                .uuid(object.getUuid())
                .build();
    }

    @Override
    public WithdrawRequest serialize(WithdrawRequestDatabaseModel model) {
        return new DatabaseWithdrawRequest(model);
    }

    private class DatabaseWithdrawRequest extends SerializableWithdrawRequest {

        public DatabaseWithdrawRequest(WithdrawRequestDatabaseModel model) {
            super(
                    model.getUuid(),
                    model.getUserUuid(),
                    WithdrawType.valueOf(model.getType()),
                    WithdrawStatus.valueOf(model.getStatus()), model.getRequisites(),
                    model.getAmount()
            );
        }
    }
}
