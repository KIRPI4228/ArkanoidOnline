package ru.arkanoid.backend.user.finance;

import lombok.Getter;
import ru.arkanoid.backend.user.finance.history.EmptyTransactionHistory;

@Getter
public class EmptyBalance extends Balance {
    public EmptyBalance() {
        super(0, new EmptyTransactionHistory());
    }
}
