package ru.arkanoid.backend.filters.finance.combo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.arkanoid.backend.filters.ParameterFilter;
import ru.arkanoid.backend.filters.ParametersComboFilter;
import ru.arkanoid.backend.filters.finance.MinWithdrawRequestFilter;
import ru.arkanoid.backend.user.finance.WithdrawRequest;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WithdrawRequestFilter extends ParametersComboFilter<WithdrawRequest> {
    private final MinWithdrawRequestFilter minWithdrawRequestFilter;

    @Override
    protected void applyFilters(List<ParameterFilter<WithdrawRequest>> parameterFilters) {
        parameterFilters.add(model -> model.getType().getFilter().doFilter(model.getRequisites()));
        parameterFilters.add(model -> minWithdrawRequestFilter.doFilter(model.getAmount()));
    }
}
