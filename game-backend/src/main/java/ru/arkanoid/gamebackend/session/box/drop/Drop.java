package ru.arkanoid.gamebackend.session.box.drop;

import ru.arkanoid.gamebackend.engine.CollisionOptions;
import ru.arkanoid.gamebackend.engine.Scale;
import ru.arkanoid.gamebackend.engine.Vector;
import ru.arkanoid.gamebackend.engine.primitives.GameObject;
import ru.arkanoid.gamebackend.engine.primitives.MovableCollider;
import ru.arkanoid.gamebackend.engine.primitives.rectangle.RectangleCollision;
import ru.arkanoid.gamebackend.engine.primitives.rectangle.RectangleGameObject;
import ru.arkanoid.gamebackend.session.levels.GameOverTrigger;
import ru.arkanoid.gamebackend.session.levels.Level;
import ru.arkanoid.gamebackend.session.platform.Platform;

import java.util.Set;

public interface Drop extends RectangleGameObject, RectangleCollision, MovableCollider {
    Vector DESTROYED_POSITION = new Vector(-100, -100, 0);
    Scale SCALE = new Scale(0.4f, 0.4f);
    float SPEED = 0.03f;

    DropType getType();

    void apply();

    default Level getLevel() {
        return (Level) getRoom();
    }

    default boolean isDestroyed() {
        return getPosition().isLessAndEqual(DESTROYED_POSITION);
    }

    @Override
    default float getSpeed() {
        return SPEED;
    }

    @Override
    default Scale getScale() {
        return SCALE;
    }

    @Override
    @Deprecated
    default void setScale(Scale scale) { }

    @Override
    @Deprecated
    default void setSpeed(float speed) { }

    @Override
    default void onUpdate() {
        move(new Vector(0, -1, 0));
    }

    @Override
    default boolean filter(GameObject gameObject) {
        return gameObject instanceof Platform || gameObject instanceof GameOverTrigger;
    }

    @Override
    default boolean onCollided(Set<CollisionOptions> collisionOptionsSet) {
        collisionOptionsSet.stream().forEach(collisionOptions -> {
            if (collisionOptions.getColliding() instanceof Platform && !isDestroyed()) {
                apply();
            }

            destroy();
        });

        return false;
    }

    @Override
    default void destroy() {
        if (isDestroyed()) {
            RectangleGameObject.super.destroy();
        } else {
            getPosition().multiply(0, 0).subtract(100, 100);
        }
    }
}
