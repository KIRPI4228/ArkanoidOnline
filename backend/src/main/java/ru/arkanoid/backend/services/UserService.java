package ru.arkanoid.backend.services;

import ru.arkanoid.backend.models.user.rest.UserChangePasswordModel;
import ru.arkanoid.backend.user.User;

public interface UserService {
    User getUser(String token);
    void changeUsername(String token, String newUsername);
    void changePassword(String token, UserChangePasswordModel model);
    void changePassword(User user, String newPassword);
}
