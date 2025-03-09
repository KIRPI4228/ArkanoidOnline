package ru.arkanoid.gamebackend.engine.primitives;

import ru.arkanoid.gamebackend.engine.Vector;

public interface MovableVectorUpdatable extends Updatable<Vector>, Movable {
    @Override
    default void move(Vector position) {
        Movable.super.move(position);

        getUpdater().accept(position);
    }
}
