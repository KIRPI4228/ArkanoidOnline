package ru.arkanoid.gamebackend.engine.primitives;

import ru.arkanoid.gamebackend.engine.CollisionOptions;
import ru.arkanoid.gamebackend.engine.primitives.line.Line;
import ru.arkanoid.gamebackend.engine.primitives.line.SimpleLine;
import ru.arkanoid.gamebackend.helpers.MathHelper;

import java.util.Set;

public interface Bouncing extends MovableCollider {
    Line getCurrentLine();
    void setCurrentLine(Line line);

    default void update() {
        move(getCurrentLine().getDirection());
    }

    @Override
    default boolean onCollided(Set<CollisionOptions> options) {
        var x = 0;
        var y = 0;

        for (var collideOptions : options) {
            x += collideOptions.getDirection().getX();
            y += collideOptions.getDirection().getY();
        }



        if (Math.abs(x) + Math.abs(y) == 0) {
            x = 1;
            y = 1;
        } else if (Math.abs(Math.signum(x)) - Math.abs(Math.signum(y)) == 0) {
            x *= MathHelper.getIntegerFromBoolean(x < 0);
            y *= MathHelper.getIntegerFromBoolean(y < 0);
        }

        setCurrentLine(new SimpleLine(
                getPosition(),
                MathHelper.getAngle(getCurrentLine().getDirection().getX() * MathHelper.getDirectionMultiplier(x),
                        getCurrentLine().getDirection().getY() * MathHelper.getDirectionMultiplier(y)),
                getRoom().getHeight() * getRoom().getWidth()
        ));

        return true;
    }
}
