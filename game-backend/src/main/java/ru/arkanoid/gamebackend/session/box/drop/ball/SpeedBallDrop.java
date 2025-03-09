package ru.arkanoid.gamebackend.session.box.drop.ball;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.arkanoid.gamebackend.engine.Room;
import ru.arkanoid.gamebackend.engine.Vector;
import ru.arkanoid.gamebackend.session.box.drop.Drop;
import ru.arkanoid.gamebackend.session.box.drop.DropType;

@Getter
@RequiredArgsConstructor
public abstract class SpeedBallDrop implements Drop {
    private final Room room;
    private final Vector position;
    private final DropType type;

    protected abstract float getMultiplier();

    @Override
    public void apply() {
        getLevel().getBall().setSpeed(getLevel().getBall().getSpeed() * getMultiplier());
    }
}
