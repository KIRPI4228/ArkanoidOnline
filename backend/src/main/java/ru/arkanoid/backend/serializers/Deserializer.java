package ru.arkanoid.backend.serializers;

import java.util.Optional;

public interface Deserializer<T, M> {
    M deserialize(T object);

    default Optional<M> deserializeOptional(T object) {
        return Optional.of(deserialize(object));
    }
}
