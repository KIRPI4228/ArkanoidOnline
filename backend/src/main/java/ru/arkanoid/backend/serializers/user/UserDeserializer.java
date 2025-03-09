package ru.arkanoid.backend.serializers.user;

import ru.arkanoid.backend.serializers.Deserializer;
import ru.arkanoid.backend.user.User;

public interface UserDeserializer<M> extends Deserializer<User, M> {
    M deserialize(User user);
}
