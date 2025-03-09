package ru.arkanoid.gamebackend.session.box.drop.platform.scale;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.arkanoid.gamebackend.engine.Room;
import ru.arkanoid.gamebackend.engine.Scale;
import ru.arkanoid.gamebackend.engine.Vector;
import ru.arkanoid.gamebackend.session.box.drop.Drop;
import ru.arkanoid.gamebackend.session.box.drop.DropType;

@RequiredArgsConstructor
@Getter
public abstract class ScalePlatformDrop implements Drop {
    private final Room room;
    private final Vector position;
    private final DropType type;

    protected abstract float getMultiplier();

    @Override
    public void apply() {
        var scale = getLevel().getPlatform().getScale();

        getLevel().getPlatform().setScale(new Scale(scale.getX() * getMultiplier(), scale.getY()));
    }
}
