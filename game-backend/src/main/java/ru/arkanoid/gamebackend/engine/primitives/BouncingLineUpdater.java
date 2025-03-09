package ru.arkanoid.gamebackend.engine.primitives;

import ru.arkanoid.gamebackend.engine.CollisionOptions;
import ru.arkanoid.gamebackend.models.LineMoveModel;

import java.util.Set;

public interface BouncingLineUpdater extends Updatable<LineMoveModel>, Bouncing {
    @Override
    default boolean onCollided(Set<CollisionOptions> options) {
        Bouncing.super.onCollided(options);

        getUpdater().accept(LineMoveModel.builder()
                .position(getCurrentLine().getFirstPosition())
                .direction(getCurrentLine().getDirection())
                .speed(getSpeed())
                .build());

        return true;
    }
}
