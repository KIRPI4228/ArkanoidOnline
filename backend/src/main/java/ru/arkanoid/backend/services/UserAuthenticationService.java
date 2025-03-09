package ru.arkanoid.backend.services;

import ru.arkanoid.backend.models.user.rest.UserJwtAuthenticationResponseModel;
import ru.arkanoid.backend.models.user.rest.UserResetPasswordModel;
import ru.arkanoid.backend.models.user.rest.UserSignInModel;
import ru.arkanoid.backend.models.user.rest.UserSignUpModel;

public interface UserAuthenticationService {
    UserJwtAuthenticationResponseModel signUp(UserSignUpModel model);
    UserJwtAuthenticationResponseModel signIn(UserSignInModel model);
    UserJwtAuthenticationResponseModel resetPassword(UserResetPasswordModel model);
    UserJwtAuthenticationResponseModel verifySignUp(String token, String key);
    UserJwtAuthenticationResponseModel verifyResetPassword(String token, String key);
    boolean isAuthorized(String token);
}
