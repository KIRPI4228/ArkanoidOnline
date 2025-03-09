package ru.arkanoid.backend.serializers;

public interface Serializer<T, M> {

    T serialize(M model);
}

