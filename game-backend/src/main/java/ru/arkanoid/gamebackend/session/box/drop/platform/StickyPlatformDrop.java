package ru.arkanoid.gamebackend.session.box.drop.platform;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.arkanoid.gamebackend.engine.Room;
import ru.arkanoid.gamebackend.engine.Vector;
import ru.arkanoid.gamebackend.engine.primitives.line.Line;
import ru.arkanoid.gamebackend.engine.primitives.line.SimpleLine;
import ru.arkanoid.gamebackend.exceptions.BooleanException;
import ru.arkanoid.gamebackend.helpers.Debug;
import ru.arkanoid.gamebackend.helpers.TimeHelper;
import ru.arkanoid.gamebackend.session.box.drop.Drop;
import ru.arkanoid.gamebackend.session.box.drop.DropType;
import ru.arkanoid.gamebackend.update.scheduler.tasks.Timer;

import java.util.concurrent.atomic.AtomicLong;

@Getter
@RequiredArgsConstructor
public class StickyPlatformDrop implements Drop {
    // TODO: 04.02.2025 Change to TimeUnit type
    private static final long STICK_DELAY = 1300; // In millis

    private final Room room;
    private final Vector position;
    private final DropType type;

    @Override
    public void apply() {
        AtomicLong expire = new AtomicLong(TimeHelper.getCurrentMillis() + STICK_DELAY);

        final var difference = getLevel().getBall().getPosition().getX() - getLevel().getPlatform().getPosition().getX();

        getLevel().getBall().setBallPlatformBounceHandler(event -> {
            getLevel().getBall().stop();
            getRoom().getScheduler().addTask(new Timer(STICK_DELAY, () -> {

            }));


            /*final var current = TimeHelper.getCurrentMillis();
            final var isExpired = expire.get() <= current;

            event.setCanceled(!isExpired);

            if (isExpired) {
                getLevel().getBall().start();
                expire.set(current + STICK_DELAY);
            } else {
                final var ball = getLevel().getBall();

                ball.stop();
                ball.getPosition().multiply(0, 0).increase(new Vector(getLevel().getPlatform().getPosition().getX() + difference, ball.getPosition().getY(), 0));

                //ball.setCurrentLine(SimpleLine.createLine(ball.getPosition(), new Vector(getLevel().getPlatform().getPosition().getX() + difference, ball.getPosition().getY(), 0)));

                throw new BooleanException(false);
            }

            Debug.log(getLevel().getBall().getCurrentLine().getDistance() + "");*/


        });
    }
}
