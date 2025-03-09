package ru.arkanoid.backend.filters.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.arkanoid.backend.filters.ParameterFilter;
import ru.arkanoid.backend.repositories.user.UserRepository;

@Component
@RequiredArgsConstructor
public class EmailContainsFilter implements ParameterFilter<String> {
    private final UserRepository repository;

    @Override
    public String doFilter(String parameter) {
        if (repository.containsUserByEmail(parameter)) {
            return "error.credentials.email.already_used";
        }
        return null;
    }
}
