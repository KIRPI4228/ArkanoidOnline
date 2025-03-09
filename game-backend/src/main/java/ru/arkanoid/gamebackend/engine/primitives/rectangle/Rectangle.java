package ru.arkanoid.gamebackend.engine.primitives.rectangle;

import ru.arkanoid.gamebackend.engine.Scale;
import ru.arkanoid.gamebackend.engine.Vector;

public interface Rectangle {
    Vector getPosition();
    Scale getScale();

    default Vector getBeginPosition() {
        return new Vector(
                getPosition().getX() - (getScale().getX() / 2),
                getPosition().getY() + (getScale().getY() / 2),
                getPosition().getZ()
        );
    }

    default Vector getEndPosition() {
        return new Vector(
                getPosition().getX() + (getScale().getX() / 2),
                getPosition().getY() - (getScale().getY() / 2),
                getPosition().getZ()
        );
    }
}
