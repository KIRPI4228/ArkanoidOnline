package ru.arkanoid.backend.user.finance;

import java.util.UUID;

public interface WithdrawRequest {
    UUID getUuid();
    WithdrawType getType();
    WithdrawStatus getStatus();
    String getRequisites();
    UUID getUserUuid();
    int getAmount();
}
