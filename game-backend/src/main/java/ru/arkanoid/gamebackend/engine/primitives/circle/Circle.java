package ru.arkanoid.gamebackend.engine.primitives.circle;

import ru.arkanoid.gamebackend.engine.Scale;
import ru.arkanoid.gamebackend.engine.Vector;

public interface Circle {
    Vector getPosition();
    Scale getScale();
    default float getRadius() {
        return getScale().getX() / 2;
    }
}
