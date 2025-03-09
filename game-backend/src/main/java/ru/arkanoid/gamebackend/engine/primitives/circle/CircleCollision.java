package ru.arkanoid.gamebackend.engine.primitives.circle;

import ru.arkanoid.gamebackend.engine.CollisionOptions;
import ru.arkanoid.gamebackend.engine.Vector;
import ru.arkanoid.gamebackend.engine.primitives.Collider;
import ru.arkanoid.gamebackend.engine.primitives.rectangle.RectangleCollision;
import ru.arkanoid.gamebackend.helpers.EngineHelper;

public interface CircleCollision extends Collider, Circle {
    @Override
    default CollisionOptions doesCollide(Collider collision) {
        if (collision instanceof RectangleCollision rectangle) {
            var sides = EngineHelper.getRectangleSideFromCircleCollision(rectangle, this);

            if (!sides.equals(new Vector(0, 0, 0))) {
                return CollisionOptions.builder()
                        .direction(sides)
                        .colliding(collision)
                        .build();
            }
        }

        return null;
    }
}
