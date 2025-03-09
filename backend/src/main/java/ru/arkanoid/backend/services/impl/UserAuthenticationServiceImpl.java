package ru.arkanoid.backend.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.arkanoid.backend.exceptions.ServiceException;
import ru.arkanoid.backend.filters.FilterActions;
import ru.arkanoid.backend.filters.user.combos.ResetPasswordFilter;
import ru.arkanoid.backend.filters.user.combos.SignUpFilter;
import ru.arkanoid.backend.models.user.rest.UserJwtAuthenticationResponseModel;
import ru.arkanoid.backend.models.user.rest.UserResetPasswordModel;
import ru.arkanoid.backend.models.user.rest.UserSignInModel;
import ru.arkanoid.backend.models.user.rest.UserSignUpModel;
import ru.arkanoid.backend.repositories.user.UserRepository;
import ru.arkanoid.backend.serializers.user.UserJwtTokenSerializer;
import ru.arkanoid.backend.serializers.user.UserResetPasswordVerificationJwtTokenSerializer;
import ru.arkanoid.backend.serializers.user.UserSignupSerializer;
import ru.arkanoid.backend.serializers.user.UserVerificationJwtTokenSerializer;
import ru.arkanoid.backend.services.*;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserAuthenticationServiceImpl implements UserAuthenticationService {
    private final UserSignupSerializer userSignupSerializer;
    private final UserJwtTokenSerializer userJwtTokenSerializer;
    private final UserVerificationJwtTokenSerializer userVerificationJwtTokenSerializer;
    private final UserResetPasswordVerificationJwtTokenSerializer userResetPasswordVerificationJwtTokenSerializer;

    private final TempKeyService tempKeyService;
    private final MailService mailService;
    private final UserService userService;
    private final JwtService jwtService;

    private final UserRepository repository;

    private final AuthenticationManager authenticationManager;

    private final SignUpFilter signUpFilter;
    private final ResetPasswordFilter resetPasswordFilter;


    @Override
    public UserJwtAuthenticationResponseModel signUp(UserSignUpModel model) {
        signUpFilter.doFilter(model, FilterActions::ThrowException);

        var user = userSignupSerializer.serialize(model);

        var key = tempKeyService.generate(user.getUuid());

        mailService.sendSignUpVerificationLetter(user.getEmail(), key);

        return new UserJwtAuthenticationResponseModel(jwtService.generateToken(userVerificationJwtTokenSerializer.deserialize(user)));
    }

    @Override
    public UserJwtAuthenticationResponseModel signIn(UserSignInModel model) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        model.getEmail(),
                        model.getPassword()
                )
        );

        var user = repository.findUserByEmail(model.getEmail());

        return new UserJwtAuthenticationResponseModel(jwtService.generateToken(userJwtTokenSerializer.deserialize(user)));
    }

    @Override
    public UserJwtAuthenticationResponseModel resetPassword(UserResetPasswordModel model) {
        resetPasswordFilter.doFilter(model, FilterActions::ThrowException);

        var user = repository.findUserByEmail(model.getEmail());

        userService.changePassword(user, model.getNewPassword());

        var key = tempKeyService.generate(user.getUuid());

        // TODO: create send reset password letter method
        mailService.sendResetPasswordVerificationLetter(user.getEmail(), key);

        return new UserJwtAuthenticationResponseModel(jwtService.generateToken(userVerificationJwtTokenSerializer.deserialize(user)));
    }

    @Override
    public UserJwtAuthenticationResponseModel verifySignUp(String token, String key) {
        var userModel = jwtService.getUserVerificationModel(token);

        isUserModelValid(userModel, key, userModel.getUuid());

        var user = userVerificationJwtTokenSerializer.serialize(userModel);
        repository.save(user);

        return new UserJwtAuthenticationResponseModel(jwtService.generateToken(userJwtTokenSerializer.deserialize(user)));
    }

    @Override
    public UserJwtAuthenticationResponseModel verifyResetPassword(String token, String key) {
        var userModel = jwtService.getUserResetPasswordVerificationModel(token);

        isUserModelValid(userModel, key, userModel.getUuid());

        var user = userResetPasswordVerificationJwtTokenSerializer.serialize(userModel);
        repository.save(user);

        return new UserJwtAuthenticationResponseModel(jwtService.generateToken(userJwtTokenSerializer.deserialize(user)));
    }

    @Override
    public boolean isAuthorized(String token) {
        return token != null
                && !token.isEmpty()
                && jwtService.getUserModel(token) != null
                && userJwtTokenSerializer.serialize(jwtService.getUserModel(token)) != null;
    }

    private void isUserModelValid(Object userModel, String key, UUID uuid) {
        if (userModel == null || !tempKeyService.accept(key, uuid)) {
            throw new ServiceException();
        }
    }
}
