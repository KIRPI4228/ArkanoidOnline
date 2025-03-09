package ru.arkanoid.backend.filters.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.arkanoid.backend.filters.ParameterFilter;
import ru.arkanoid.backend.services.UserService;

@Component
@RequiredArgsConstructor
public class AuthorizedFilter implements ParameterFilter<String> {
    //private final UserService userService;

    @Override
    public String doFilter(String token) {
        //if (!userService.isAuthorized(token)) {
            return "error.session.authorized"; // TODO: key
        //}

        //return null;
    }
}
