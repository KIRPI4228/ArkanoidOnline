package ru.arkanoid.backend.filters.user;

import org.springframework.stereotype.Component;
import ru.arkanoid.backend.filters.ParameterFilter;

@Component
public class PasswordCreationFilter implements ParameterFilter<String> {
    private static final int MAX_PASSWORD_LENGTH = 255;

    @Override
    public String doFilter(String parameter) {
        if (parameter.length() > MAX_PASSWORD_LENGTH) {
            return "error.credentials.password.too_long";
        } else if (parameter.isEmpty() || parameter.isBlank() || parameter.contains(" ")) {
            return "error.credentials.password.empty";
        }
        return null;
    }
}
