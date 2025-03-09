package ru.arkanoid.gamebackend.session.platform;

import ru.arkanoid.gamebackend.engine.*;
import ru.arkanoid.gamebackend.engine.primitives.*;
import ru.arkanoid.gamebackend.engine.primitives.rectangle.RectangleCollision;
import ru.arkanoid.gamebackend.engine.primitives.rectangle.RectangleGameObject;
import ru.arkanoid.gamebackend.helpers.EngineHelper;
import ru.arkanoid.gamebackend.helpers.MathHelper;
import ru.arkanoid.gamebackend.session.levels.Level;

public interface Platform extends RectangleGameObject, RectangleCollision, MovableCollider {
    Runnable getUpdater();

    void start(float direction);
    void stop();

    @Override
    default void onUpdate() {
        getUpdater().run();
    }

    default Level getLevel() {
        return (Level) getRoom();
    }

    default void move(float direction) {
        move(new Vector(Math.min(Math.abs(direction), 1) * Integer.signum((int)direction) * MathHelper.getIntegerFromBoolean(getLevel().getBall().isStarted()), 0, 0));
    }

    @Override
    default boolean filter(GameObject gameObject) {
        return gameObject instanceof RoomBorder;
    }

    @Override
    default CollisionOptions doesCollide(Collider collision) {
        var border = ((RoomBorder)collision);

        if (border.getScale().getY() > 1 && EngineHelper.isPointInRectangle(getPosition(), border.getBeginPosition(), border.getEndPosition())) {
            return CollisionOptions.builder()
                    .colliding(collision)
                    .build();
        }

        return null;
    }
}
