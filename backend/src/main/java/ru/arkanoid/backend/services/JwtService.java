package ru.arkanoid.backend.services;

import ru.arkanoid.backend.models.user.UserJwtTokenModel;
import ru.arkanoid.backend.models.user.UserResetPasswordVerificationJwtTokenModel;
import ru.arkanoid.backend.models.user.UserVerificationJwtTokenModel;
import ru.arkanoid.backend.user.User;

public interface JwtService {
    UserJwtTokenModel getUserModel(String token);
    UserVerificationJwtTokenModel getUserVerificationModel(String token);
    UserResetPasswordVerificationJwtTokenModel getUserResetPasswordVerificationModel(String token);

    String generateToken(UserJwtTokenModel model);
    String generateToken(UserVerificationJwtTokenModel model);
    String generateToken(UserResetPasswordVerificationJwtTokenModel model);

    boolean isTokenValid(String token, User user);
    boolean isTokenTrusted(String token);
}
