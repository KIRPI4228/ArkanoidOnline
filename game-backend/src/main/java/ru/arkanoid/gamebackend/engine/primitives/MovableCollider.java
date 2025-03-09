package ru.arkanoid.gamebackend.engine.primitives;

import ru.arkanoid.gamebackend.engine.CollisionOptions;
import ru.arkanoid.gamebackend.engine.Room;
import ru.arkanoid.gamebackend.engine.Vector;

import java.util.Set;

public interface MovableCollider extends Movable, Collider {
    Room getRoom();

    @Override
    default void move(Vector position) {
        var oldPosition = getPosition().clone();

        Movable.super.move(position);

        var options = getCollisions();
        if (options != null && !options.isEmpty() && onCollided(options)) {
            getPosition().setX(oldPosition.getX());
            getPosition().setY(oldPosition.getY());
            getPosition().setZ(oldPosition.getZ());
        }
    }

    default boolean onCollided(Set<CollisionOptions> options) {
        return true;
    }

    private Set<CollisionOptions> getCollisions() {
        return getRoom().findCollidedGameObjects(this);
    }
}
