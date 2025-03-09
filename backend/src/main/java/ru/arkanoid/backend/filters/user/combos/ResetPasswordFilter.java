package ru.arkanoid.backend.filters.user.combos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.arkanoid.backend.filters.ParametersComboFilter;
import ru.arkanoid.backend.filters.ParameterFilter;
import ru.arkanoid.backend.filters.user.*;
import ru.arkanoid.backend.models.user.rest.UserResetPasswordModel;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ResetPasswordFilter extends ParametersComboFilter<UserResetPasswordModel> {
    private final EmailCreationFilter emailCreationFilter;
    private final EmailDoesntContainFilter emailDoesntContainFilter;
    private final PasswordCreationFilter passwordCreationFilter;

    @Override
    protected void applyFilters(List<ParameterFilter<UserResetPasswordModel>> filters) {
        filters.add(model -> emailCreationFilter.doFilter(model.getEmail()));
        filters.add(model -> passwordCreationFilter.doFilter(model.getNewPassword()));

        filters.add(model -> emailDoesntContainFilter.doFilter(model.getEmail()));
    }
}
