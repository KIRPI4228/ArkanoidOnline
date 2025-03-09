package ru.arkanoid.backend.filters.user;

import org.springframework.stereotype.Component;
import ru.arkanoid.backend.filters.ParameterFilter;

import java.util.regex.Pattern;

@Component
public class EmailCreationFilter implements ParameterFilter<String> {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$");

    @Override
    public String doFilter(String parameter) {
        if (!EMAIL_PATTERN.matcher(parameter).matches()) {
            return "error.credentials.email.invalid_format";
        }
        return null;
    }
}
