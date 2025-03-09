package ru.arkanoid.gamebackend.engine.primitives;

import ru.arkanoid.gamebackend.engine.Vector;

public interface Movable {
    Vector getPosition();

    float getSpeed();
    void setSpeed(float speed);


    default void move(Vector direction) {
        getPosition().increase(direction.getX() * getSpeed(), direction.getY() * getSpeed());
    }
}
