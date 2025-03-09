package ru.arkanoid.gamebackend.session.platform;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.arkanoid.gamebackend.engine.Room;
import ru.arkanoid.gamebackend.engine.Vector;
import ru.arkanoid.gamebackend.engine.Scale;

@Getter
@RequiredArgsConstructor
public class StandardPlatform implements Platform {
    private final Room room;

    private Runnable updater = () -> {};

    @Setter
    private float speed = 1;
    @Setter
    private Vector position = new Vector(0, -3, 0);
    @Setter
    private Scale scale = new Scale(2.5f, 0.3f);

    @Override
    public void start(float direction) {
        updater = () -> move(direction);
    }

    @Override
    public void stop() {
        updater = () -> {};
    }
}
