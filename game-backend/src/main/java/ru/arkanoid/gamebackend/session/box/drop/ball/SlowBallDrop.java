package ru.arkanoid.gamebackend.session.box.drop.ball;

import ru.arkanoid.gamebackend.engine.Room;
import ru.arkanoid.gamebackend.engine.Vector;
import ru.arkanoid.gamebackend.session.box.drop.DropType;

public class SlowBallDrop extends SpeedBallDrop {
    private static final float SPEED_SUBTRACT_MULTIPLIER = 0.70f;

    public SlowBallDrop(Room room, Vector position, DropType type) {
        super(room, position, type);
    }

    @Override
    protected float getMultiplier() {
        return SPEED_SUBTRACT_MULTIPLIER;
    }
}
