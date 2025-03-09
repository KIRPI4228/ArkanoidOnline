package ru.arkanoid.gamebackend.engine.primitives.rectangle;

import ru.arkanoid.gamebackend.engine.primitives.GameObject;
import ru.arkanoid.gamebackend.helpers.EngineHelper;
import ru.arkanoid.gamebackend.engine.Vector;

public interface RectangleGameObject extends GameObject, Rectangle {
    @Override    default boolean matchPosition(Vector position) {
        return EngineHelper.isPointInRectangle(position, getBeginPosition(), getEndPosition());
    }
}
