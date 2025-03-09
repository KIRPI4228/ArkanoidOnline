package ru.arkanoid.backend.serializers.user;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.arkanoid.backend.helpers.UserHelper;
import ru.arkanoid.backend.models.user.rest.UserSignUpModel;
import ru.arkanoid.backend.user.Role;
import ru.arkanoid.backend.user.User;
import ru.arkanoid.backend.user.finance.EmptyWallet;

@Component
@AllArgsConstructor
public class UserSignupSerializer implements UserSerializer<UserSignUpModel> {
    private final PasswordEncoder passwordEncoder;


    @Override
    public User serialize(UserSignUpModel model) {
        return new SignedUpUser(model);
    }


    private class SignedUpUser extends SerializableUser {
        public SignedUpUser(UserSignUpModel model) {
            super(
                    UserHelper.getUuidFromEmail(model.getEmail()),
                    Role.USER,
                    model.getUsername(),
                    model.getEmail(),
                    passwordEncoder.encode(model.getPassword()),
                    model.getReferral(),
                    new EmptyWallet()
            );
        }
    }
}
