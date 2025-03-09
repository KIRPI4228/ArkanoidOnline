package ru.arkanoid.gamebackend.session;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.arkanoid.gamebackend.session.levels.Level;
import ru.arkanoid.gamebackend.update.scheduler.tasks.Timer;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class StandardSession implements Session {
    private final String key;
    private final String token;
    private final String ip;
    private final long exitTimerDuration;
    @Setter
    private Level level;

    private final UUID uuid = UUID.randomUUID();
    private int score;

    public void addScore(int amount) {
        score += amount;
    }

    @Override
    public void close() {
        getLevel().exit();
    }

    @Override
    public void startExitTimer() {
        getLevel().getScheduler().addTask(new Timer(getExitTimerDuration(), this::close));
    }
}
