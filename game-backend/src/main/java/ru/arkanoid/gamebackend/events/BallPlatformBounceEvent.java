package ru.arkanoid.gamebackend.events;

import lombok.Getter;
import lombok.Setter;
import ru.arkanoid.gamebackend.session.ball.Ball;


public class BallPlatformBounceEvent extends BallEvent implements CancelableEvent {
    @Getter
    @Setter
    private boolean canceled = false;

    public BallPlatformBounceEvent(Ball ball) {
        super(ball);
    }
}
