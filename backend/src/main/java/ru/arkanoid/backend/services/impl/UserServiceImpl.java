package ru.arkanoid.backend.services.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.arkanoid.backend.exceptions.ServiceException;
import ru.arkanoid.backend.filters.FilterActions;
import ru.arkanoid.backend.filters.user.PasswordCreationFilter;
import ru.arkanoid.backend.filters.user.UsernameCreationFilter;
import ru.arkanoid.backend.models.user.rest.*;
import ru.arkanoid.backend.repositories.user.UserRepository;
import ru.arkanoid.backend.serializers.user.UserJwtTokenSerializer;
import ru.arkanoid.backend.services.JwtService;
import ru.arkanoid.backend.services.UserService;
import ru.arkanoid.backend.user.User;

@Primary
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository repository;

    private final UserJwtTokenSerializer userJwtTokenSerializer;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final PasswordCreationFilter passwordCreationFilter;
    private final UsernameCreationFilter usernameCreationFilter;

    @Override
    public User getUser(String token) {
        var model = jwtService.getUserModel(token);

        if (model == null) {
            throw new ServiceException("error.session.not_valid");
        }

        var user = repository.findUserByUuid(model.getUuid());

        if (user == null) {
            throw new ServiceException("error.session.not_valid");
        }

        return user;
    }

    @Override
    public void changeUsername(String token, String newUsername) {
        usernameCreationFilter.doFilter(newUsername, FilterActions::ThrowException);

        var user = userJwtTokenSerializer.serialize(jwtService.getUserModel(token));

        user.setUsername(newUsername);

        repository.save(user);
    }

    @Override
    public void changePassword(String token, UserChangePasswordModel model) {
        passwordCreationFilter.doFilter(model.getNewPassword(), FilterActions::ThrowException);

        var user = userJwtTokenSerializer.serialize(jwtService.getUserModel(token));

        if (!passwordEncoder.matches(model.getOldPassword(), user.getPassword())) {
            throw new ServiceException("error.credentials.password.incorrect_old_password");
        }

        changePassword(user, model.getNewPassword());

        // TODO: send letter

        repository.save(user);
    }

    @Override
    public void changePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
    }
}
