package ru.arkanoid.gamebackend.engine.primitives.circle;

import ru.arkanoid.gamebackend.engine.primitives.GameObject;
import ru.arkanoid.gamebackend.engine.Vector;
import ru.arkanoid.gamebackend.engine.primitives.line.SimpleLine;

public interface CircleGameObject extends GameObject, Circle {
    @Override
    default boolean matchPosition(Vector position) {
        var ray = SimpleLine.createLine(getPosition(), position);

        return ray.getDistance() < getRadius();
    }
}
