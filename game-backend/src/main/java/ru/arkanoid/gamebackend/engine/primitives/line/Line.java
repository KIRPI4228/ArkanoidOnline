package ru.arkanoid.gamebackend.engine.primitives.line;

import ru.arkanoid.gamebackend.engine.Vector;

public interface Line {
    Vector getFirstPosition();
    Vector getSecondPosition();
    Vector getDirection();

    float getAngle();
    float getDistance();


}
