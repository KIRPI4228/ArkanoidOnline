package ru.arkanoid.gamebackend.engine.primitives;

import ru.arkanoid.gamebackend.engine.CollisionOptions;

public interface Collider {
    default boolean filter(GameObject gameObject) {
        return true;
    }

    CollisionOptions doesCollide(Collider collision);
}
