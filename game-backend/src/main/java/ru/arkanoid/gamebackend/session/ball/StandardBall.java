package ru.arkanoid.gamebackend.session.ball;

import lombok.Getter;
import lombok.Setter;
import ru.arkanoid.gamebackend.engine.primitives.line.Line;
import ru.arkanoid.gamebackend.engine.primitives.line.SimpleLine;
import ru.arkanoid.gamebackend.engine.Room;
import ru.arkanoid.gamebackend.engine.Vector;
import ru.arkanoid.gamebackend.engine.Scale;
import ru.arkanoid.gamebackend.events.BallPlatformBounceEvent;
import ru.arkanoid.gamebackend.helpers.Debug;
import ru.arkanoid.gamebackend.session.Session;

import java.util.function.Consumer;

@Getter
public class StandardBall implements Ball {
    private final Room room;
    private final Session session;

    private boolean isStarted = false;
    private Runnable updater = () -> {};

    @Setter
    private Consumer<BallPlatformBounceEvent> ballPlatformBounceHandler = event -> {};
    @Setter
    private float speed = 0.7f;
    @Setter
    private Line currentLine;
    @Setter
    private Vector position = new Vector(0, -2.4f, 0);
    @Setter
    private Scale scale = new Scale(0.4f, 0);

    public StandardBall(Room room, Session session) {
        this.room = room;
        this.session = session;
        currentLine = new SimpleLine(this.getPosition(), 94, this.getRoom().getHeight() * this.getRoom().getWidth());
    }

    @Override
    public void start() {
        isStarted = true;
        updater = this::update;
    }

    @Override
    public void stop() {
        updater = () -> {};
    }

    public void setSpeed(float speed) {
        if (speed >= getRadius() || speed <= getRadius() * 0.07) {
            return;
        }
        this.speed = speed;
    }
}
