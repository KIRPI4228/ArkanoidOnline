package ru.arkanoid.backend.user.finance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.arkanoid.backend.exceptions.ServiceException;
import ru.arkanoid.backend.user.finance.history.DeductTransactionHistoryNode;
import ru.arkanoid.backend.user.finance.history.TopUpTransactionHistoryNode;
import ru.arkanoid.backend.user.finance.history.TransactionsHistory;

@AllArgsConstructor
@Getter
public abstract class Balance {
    private int balance;
    private TransactionsHistory history;

    public void topUp(int amount, String description) {
        if (Integer.signum(amount) == -1) {
            throw new ServiceException("error.transactions.amount_is_negative");
        } else if (amount == 0) {
            throw new ServiceException();
        }

        balance += amount;
        history.addNode(new TopUpTransactionHistoryNode(description, amount, balance));
    }
    public void deduct(int amount, String description) {
        if (Integer.signum(amount) == -1) {
            throw new ServiceException("error.transactions.amount_is_negative");
        } else if (amount > balance) {
            throw new ServiceException("error.transactions.not_enough_balance");
        } else if (amount == 0) {
            throw new ServiceException();
        }

        balance -= amount;
        history.addNode(new DeductTransactionHistoryNode(description, amount, balance));
    }
}
