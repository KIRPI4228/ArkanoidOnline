package ru.arkanoid.gamebackend.instances.engine.primitives;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.arkanoid.gamebackend.engine.Room;
import ru.arkanoid.gamebackend.engine.Scale;
import ru.arkanoid.gamebackend.engine.Vector;
import ru.arkanoid.gamebackend.engine.primitives.rectangle.RectangleCollision;
import ru.arkanoid.gamebackend.engine.primitives.rectangle.RectangleGameObject;

@AllArgsConstructor
@Getter
public class RectangleTestInstance implements RectangleGameObject, RectangleCollision {
    private Room room;
    private Vector position;

    @Setter
    private Scale scale;
}
