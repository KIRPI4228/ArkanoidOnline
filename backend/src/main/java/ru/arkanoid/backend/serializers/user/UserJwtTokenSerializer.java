package ru.arkanoid.backend.serializers.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.arkanoid.backend.models.user.UserJwtTokenModel;
import ru.arkanoid.backend.repositories.user.UserRepository;
import ru.arkanoid.backend.user.User;

@Component
@RequiredArgsConstructor
public class UserJwtTokenSerializer implements UserSerializer<UserJwtTokenModel>, UserDeserializer<UserJwtTokenModel> {

    private final UserRepository repository;

    @Override
    public User serialize(UserJwtTokenModel model) {
        return repository.findUserByUuid(model.getUuid());
    }

    @Override
    public UserJwtTokenModel deserialize(User user) {
        return UserJwtTokenModel.builder()
                .uuid(user.getUuid())
                .email(user.getEmail())
                .build();
    }
}
