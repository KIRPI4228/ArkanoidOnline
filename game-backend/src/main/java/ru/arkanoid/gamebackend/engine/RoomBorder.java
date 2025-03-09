package ru.arkanoid.gamebackend.engine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.arkanoid.gamebackend.engine.primitives.GameObject;
import ru.arkanoid.gamebackend.engine.primitives.rectangle.RectangleCollision;
import ru.arkanoid.gamebackend.engine.primitives.rectangle.RectangleGameObject;

@Getter
@AllArgsConstructor
public class RoomBorder implements RectangleGameObject, RectangleCollision {
    private final Room room;
    private final Vector position;
    @Setter
    private Scale scale;

    @Override
    public boolean filter(GameObject gameObject) {
        return false;
    }
}
