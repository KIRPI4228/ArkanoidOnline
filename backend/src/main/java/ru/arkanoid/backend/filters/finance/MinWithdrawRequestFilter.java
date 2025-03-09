package ru.arkanoid.backend.filters.finance;

import org.springframework.stereotype.Component;
import ru.arkanoid.backend.filters.ParameterFilter;

@Component
public class MinWithdrawRequestFilter implements ParameterFilter<Integer> {
    private static final int MIN_WITHDRAW = 300;

    @Override
    public String doFilter(Integer parameter) {
        if (parameter < MIN_WITHDRAW) {
            return "error.transactions.min.withdraw|{\"minimum\": \"" + MIN_WITHDRAW + "\"}";
        }
        return null;
    }
}
