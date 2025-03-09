package ru.arkanoid.backend.user.finance;

import lombok.Getter;
import ru.arkanoid.backend.filters.ParameterFilter;
import ru.arkanoid.backend.filters.finance.CreditCardFilter;
import ru.arkanoid.backend.filters.finance.CryptoWalletFilter;


public enum WithdrawType {
    CREDIT_CARD(new CreditCardFilter()),
    CRYPTO_WALLET(new CryptoWalletFilter());

    WithdrawType(ParameterFilter<String> filter) {
        this.filter = filter;
    }

    @Getter
    private ParameterFilter<String> filter;
}
