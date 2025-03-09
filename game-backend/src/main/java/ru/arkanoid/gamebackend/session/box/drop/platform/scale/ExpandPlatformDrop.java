package ru.arkanoid.gamebackend.session.box.drop.platform.scale;

import ru.arkanoid.gamebackend.engine.Room;
import ru.arkanoid.gamebackend.engine.Vector;
import ru.arkanoid.gamebackend.session.box.drop.DropType;

public class ExpandPlatformDrop extends ScalePlatformDrop {
    private static final float EXPAND_MULTIPLIER = 1.4f;

    public ExpandPlatformDrop(Room room, Vector position, DropType type) {
        super(room, position, type);
    }

    @Override
    protected float getMultiplier() {
        return EXPAND_MULTIPLIER;
    }
}
