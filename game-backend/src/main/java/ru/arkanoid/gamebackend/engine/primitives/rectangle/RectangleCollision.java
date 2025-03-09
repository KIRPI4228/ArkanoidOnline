package ru.arkanoid.gamebackend.engine.primitives.rectangle;

import ru.arkanoid.gamebackend.engine.CollisionOptions;
import ru.arkanoid.gamebackend.engine.primitives.Collider;
import ru.arkanoid.gamebackend.helpers.EngineHelper;

public interface RectangleCollision extends Rectangle, Collider {
    @Override
    default CollisionOptions doesCollide(Collider collision) {
        if (collision instanceof RectangleCollision rectangle && EngineHelper.doesRectangleCollideRectangle(this, rectangle)) {
            return CollisionOptions.builder()
                    .colliding(collision)
                    .build();
        }
        return null;
    }
}
