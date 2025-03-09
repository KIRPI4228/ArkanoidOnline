package ru.arkanoid.gamebackend.engine.primitives;

import ru.arkanoid.gamebackend.engine.Vector;

public interface MovableCollisionVectorUpdatable extends Updatable<Vector>, MovableCollider {
    @Override
    default void move(Vector position) {
        MovableCollider.super.move(position);

        getUpdater().accept(getPosition());
    }
}
