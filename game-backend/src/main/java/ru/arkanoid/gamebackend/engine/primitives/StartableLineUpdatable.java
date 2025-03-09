package ru.arkanoid.gamebackend.engine.primitives;

import ru.arkanoid.gamebackend.engine.Vector;
import ru.arkanoid.gamebackend.models.LineMoveModel;

public interface StartableLineUpdatable extends Updatable<LineMoveModel>, Movable {
    default void start(Vector direction) {
        getUpdater().accept(LineMoveModel.builder()
                        .position(getPosition())
                        .direction(direction)
                        .speed(getSpeed())
                .build());
    }

    default void stop() {
        getUpdater().accept(LineMoveModel.builder()
                .position(getPosition())
                .direction(new Vector())
                .speed(0)
                .build());
    }
}
