package ru.arkanoid.gamebackend.session.box.drop.ball;

import ru.arkanoid.gamebackend.engine.Room;
import ru.arkanoid.gamebackend.engine.Vector;
import ru.arkanoid.gamebackend.session.box.drop.DropType;

public class FastBallDrop extends SpeedBallDrop {
    private static final float SPEED_INCREASE_MULTIPLIER = 2;

    public FastBallDrop(Room room, Vector position, DropType type) {
        super(room, position, type);
    }

    @Override
    protected float getMultiplier() {
        return SPEED_INCREASE_MULTIPLIER;
    }
}
