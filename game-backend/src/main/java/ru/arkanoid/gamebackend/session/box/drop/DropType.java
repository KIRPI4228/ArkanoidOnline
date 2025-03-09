package ru.arkanoid.gamebackend.session.box.drop;

import ru.arkanoid.gamebackend.engine.Room;
import ru.arkanoid.gamebackend.engine.Vector;
import ru.arkanoid.gamebackend.session.box.drop.ball.FastBallDrop;
import ru.arkanoid.gamebackend.session.box.drop.ball.SlowBallDrop;
import ru.arkanoid.gamebackend.session.box.drop.platform.scale.ExpandPlatformDrop;
import ru.arkanoid.gamebackend.session.box.drop.platform.scale.ShrinkPlatformDrop;

import java.util.Random;

public enum DropType {
    NONE(DropType::none),
    PLATFORM_EXPAND(ExpandPlatformDrop::new),
    PLATFORM_SHRINK(ShrinkPlatformDrop::new),
    BALL_FAST(FastBallDrop::new),
    BALL_SLOW(SlowBallDrop::new),

    RANDOM(DropType::random);

    private static final Random random = new Random();
    private static final int NONE_PERCENT = 80;

    private DropBuilder builder;

    DropType(DropBuilder builder) {
        this.builder = builder;
    }

    public Drop build(Room room, Vector position) {
        return builder.build(room, position, this);
    }

    private static Drop random(Room room, Vector position, DropType type) {
        var drops = DropType.values().length-2;

        var randomIndex = random.nextInt((int) (drops + NONE_PERCENT * 0.1 * DropType.values().length));

        var drop = drops - randomIndex;
        var dropMultiplier = Math.signum(Math.signum(drop) + 1);

        return DropType.values()[(int) (dropMultiplier * (drop + 1))].build(room, position);
    }

    private static Drop none(Room room, Vector position, DropType type) {
        return null;
    }


    public interface DropBuilder {
        Drop build(Room room, Vector position, DropType type);
    }
}
