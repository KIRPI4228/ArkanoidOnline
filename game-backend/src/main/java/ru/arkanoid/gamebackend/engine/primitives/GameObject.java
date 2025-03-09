package ru.arkanoid.gamebackend.engine.primitives;

import ru.arkanoid.gamebackend.engine.Room;
import ru.arkanoid.gamebackend.engine.Scale;
import ru.arkanoid.gamebackend.engine.Vector;

public interface GameObject {
    Vector getPosition();
    Scale getScale();
    Room getRoom();

    void setScale(Scale scale);

    boolean matchPosition(Vector position);
    default void destroy() {
        getRoom().remove(this);
    }

    default void onUpdate() {

    }
}
