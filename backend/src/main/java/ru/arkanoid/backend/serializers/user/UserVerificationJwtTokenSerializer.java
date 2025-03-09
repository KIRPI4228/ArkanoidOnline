package ru.arkanoid.backend.serializers.user;

import org.springframework.stereotype.Component;
import ru.arkanoid.backend.models.user.UserVerificationJwtTokenModel;
import ru.arkanoid.backend.user.User;
import ru.arkanoid.backend.user.finance.EmptyWallet;

@Component
public class UserVerificationJwtTokenSerializer implements UserSerializer<UserVerificationJwtTokenModel>, UserDeserializer<UserVerificationJwtTokenModel> {
    @Override
    public User serialize(UserVerificationJwtTokenModel model) {
        return new VerificationJwtTokenUser(model);
    }

    @Override
    public UserVerificationJwtTokenModel deserialize(User user) {
        return UserVerificationJwtTokenModel.builder()
                .uuid(user.getUuid())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .referral(user.getReferral())
                .build();
    }

    private class VerificationJwtTokenUser extends SerializableUser {
        public VerificationJwtTokenUser(UserVerificationJwtTokenModel model) {
            super(
                    model.getUuid(),
                    model.getRole(),
                    model.getUsername(),
                    model.getEmail(),
                    model.getPassword(),
                    model.getReferral(),
                    new EmptyWallet()
            );
        }
    }
}
