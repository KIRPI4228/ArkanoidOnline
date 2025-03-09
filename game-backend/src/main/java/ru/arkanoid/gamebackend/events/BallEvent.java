package ru.arkanoid.gamebackend.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.arkanoid.gamebackend.session.ball.Ball;

@RequiredArgsConstructor
@Getter
public abstract class BallEvent implements Event {
    private final Ball ball;
}
