package ru.arkanoid.gamebackend.session.box.drop.platform.scale;

import ru.arkanoid.gamebackend.engine.Room;
import ru.arkanoid.gamebackend.engine.Vector;
import ru.arkanoid.gamebackend.session.box.drop.DropType;


public class ShrinkPlatformDrop extends ScalePlatformDrop {
    private static final float SHRINK_MULTIPLIER = 0.6f;

    public ShrinkPlatformDrop(Room room, Vector position, DropType type) {
        super(room, position, type);
    }

    @Override
    protected float getMultiplier() {
        return SHRINK_MULTIPLIER;
    }
}
