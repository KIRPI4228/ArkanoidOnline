package ru.arkanoid.backend.filters.user;

import org.springframework.stereotype.Component;
import ru.arkanoid.backend.filters.ParameterFilter;

import java.util.regex.Pattern;

@Component
public class UsernameCreationFilter implements ParameterFilter<String> {
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9._-]{3,}$");

    @Override
    public String doFilter(String parameter) {
        if (!USERNAME_PATTERN.matcher(parameter).matches()) {
            return "error.credentials.username.invalid_format";
        }
        return null;
    }
}
