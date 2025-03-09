package ru.arkanoid.backend.serializers.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.arkanoid.backend.exceptions.ServiceException;
import ru.arkanoid.backend.models.user.UserResetPasswordVerificationJwtTokenModel;
import ru.arkanoid.backend.repositories.user.UserRepository;
import ru.arkanoid.backend.user.User;

@Component
@RequiredArgsConstructor
public class UserResetPasswordVerificationJwtTokenSerializer implements UserSerializer<UserResetPasswordVerificationJwtTokenModel> {
    private final UserRepository repository;

    @Override
    public User serialize(UserResetPasswordVerificationJwtTokenModel model) {
        return new ResetPasswordUser(model);
    }

    private class ResetPasswordUser extends SerializableUser {

        public ResetPasswordUser(UserResetPasswordVerificationJwtTokenModel model) {
            super(model.getUuid(), null, null, model.getEmail(), model.getPassword(), model.getReferral(), null);

            User user = repository.findUserByUuid(model.getUuid());
            if (user == null) {
                throw new ServiceException();
            }

            setRole(user.getRole());
            setUsername(user.getUsername());
            setWallet(user.getWallet());
        }
    }
}
