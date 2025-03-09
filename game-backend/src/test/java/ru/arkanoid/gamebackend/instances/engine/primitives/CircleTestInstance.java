package ru.arkanoid.gamebackend.instances.engine.primitives;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.arkanoid.gamebackend.engine.Room;
import ru.arkanoid.gamebackend.engine.Scale;
import ru.arkanoid.gamebackend.engine.Vector;
import ru.arkanoid.gamebackend.engine.primitives.circle.CircleCollision;
import ru.arkanoid.gamebackend.engine.primitives.circle.CircleGameObject;

@AllArgsConstructor
@Getter
public class CircleTestInstance implements CircleGameObject, CircleCollision {
    private Room room;
    private Vector position;

    @Setter
    private Scale scale;
}
