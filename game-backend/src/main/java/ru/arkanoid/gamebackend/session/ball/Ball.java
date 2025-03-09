package ru.arkanoid.gamebackend.session.ball;

import ru.arkanoid.gamebackend.engine.*;
import ru.arkanoid.gamebackend.engine.primitives.Bouncing;
import ru.arkanoid.gamebackend.engine.primitives.GameObject;
import ru.arkanoid.gamebackend.engine.primitives.circle.CircleCollision;
import ru.arkanoid.gamebackend.engine.primitives.circle.CircleGameObject;
import ru.arkanoid.gamebackend.engine.primitives.line.SimpleLine;
import ru.arkanoid.gamebackend.events.BallPlatformBounceEvent;
import ru.arkanoid.gamebackend.exceptions.BooleanException;
import ru.arkanoid.gamebackend.session.Session;
import ru.arkanoid.gamebackend.session.box.Box;
import ru.arkanoid.gamebackend.session.box.drop.Drop;
import ru.arkanoid.gamebackend.session.levels.GameOverTrigger;
import ru.arkanoid.gamebackend.session.platform.Platform;

import java.util.Set;
import java.util.function.Consumer;

public interface Ball extends CircleGameObject, CircleCollision, Bouncing {
    Session getSession();
    Runnable getUpdater();

    boolean isStarted();
    void start();
    void stop();

    Consumer<BallPlatformBounceEvent> getBallPlatformBounceHandler();
    void setBallPlatformBounceHandler(Consumer<BallPlatformBounceEvent> handler);



    @Override
    default void onUpdate() {
        getUpdater().run();
    }

    @Override
    default boolean filter(GameObject gameObject) {
        return !(gameObject instanceof Drop);
    }

    @Override
    default boolean onCollided(Set<CollisionOptions> collideOptionsSet) {
        try {
            collideOptionsSet.stream().forEach(options -> {
                if (options.getColliding() instanceof Platform platform) {
                    if (options.getDirection().getY() == 1) {
                        var event = new BallPlatformBounceEvent(this);
                        getBallPlatformBounceHandler().accept(event);
                        if (!event.isCanceled()) {
                            setCurrentLine(new SimpleLine(
                                    getPosition(),
                                    86 - 70 * -((platform.getPosition().getX() - getPosition().getX()) / (platform.getScale().getX() / 2)),
                                    getRoom().getHeight() * getRoom().getWidth()
                            ));
                        }
                    }

                    throw new BooleanException(false);
                } else if (options.getColliding() instanceof GameOverTrigger gameOverTrigger) {
                    gameOverTrigger.gameOver();

                    //throw new RuntimeException();
                }
            });
        } catch (BooleanException exception) {
            return exception.getState();
        }


        if (collideOptionsSet.stream().findFirst().orElse(null).getColliding() instanceof Box box) {
            box.hit(getSession());
        }

        return Bouncing.super.onCollided(collideOptionsSet);
    }
}
