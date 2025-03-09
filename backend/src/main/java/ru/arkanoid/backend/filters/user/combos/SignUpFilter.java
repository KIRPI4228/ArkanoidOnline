package ru.arkanoid.backend.filters.user.combos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.arkanoid.backend.filters.ParametersComboFilter;
import ru.arkanoid.backend.filters.ParameterFilter;
import ru.arkanoid.backend.filters.user.*;
import ru.arkanoid.backend.models.user.rest.UserSignUpModel;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SignUpFilter extends ParametersComboFilter<UserSignUpModel> {
    private final EmailCreationFilter emailCreationFilter;
    private final EmailContainsFilter emailContainsFilter;
    private final UsernameCreationFilter usernameCreationFilter;
    private final PasswordCreationFilter passwordCreationFilter;

    @Override
    protected void applyFilters(List<ParameterFilter<UserSignUpModel>> filters) {
        filters.add(model -> emailCreationFilter.doFilter(model.getEmail()));
        filters.add(model -> usernameCreationFilter.doFilter(model.getUsername()));
        filters.add(model -> passwordCreationFilter.doFilter(model.getPassword()));

        filters.add(model -> emailContainsFilter.doFilter(model.getEmail()));
    }
}
