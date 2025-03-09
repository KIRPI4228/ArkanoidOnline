package ru.arkanoid.backend.serializers.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.arkanoid.backend.models.user.UserDatabaseModel;
import ru.arkanoid.backend.serializers.finance.WalletDatabaseSerializer;
import ru.arkanoid.backend.user.Role;
import ru.arkanoid.backend.user.User;

@Component
@RequiredArgsConstructor
public class UserDatabaseSerializer implements UserSerializer<UserDatabaseModel>, UserDeserializer<UserDatabaseModel> {
    private final WalletDatabaseSerializer walletSerializer;

    @Override
    public User serialize(UserDatabaseModel model) {
        if (model == null) {
            return null;
        }
        return new DatabaseUser(model);
    }

    @Override
    public UserDatabaseModel deserialize(User user) {
        return UserDatabaseModel.builder()
                .uuid(user.getUuid())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole().name())
                .referral(user.getReferral())
                .wallet(walletSerializer.deserialize(user.getWallet()))
                .build();
    }


    private class DatabaseUser extends SerializableUser {
        public DatabaseUser(UserDatabaseModel model) {
            super(
                    model.getUuid(),
                    Role.valueOf(model.getRole()),
                    model.getUsername(),
                    model.getEmail(),
                    model.getPassword(),
                    model.getReferral(),
                    walletSerializer.serialize(model.getWallet())
            );
        }
    }
}
