package ru.arkanoid.gamebackend.update.scheduler.tasks;

import lombok.Getter;
import ru.arkanoid.gamebackend.helpers.TimeHelper;

import java.util.UUID;

@Getter
public class Timer implements SchedulerTask {
    private final UUID id = UUID.randomUUID();

    private long duration;
    private long startTime;
    private long endTime;

    private final Runnable runnable;

    public Timer(long duration, Runnable runnable) {
        this.runnable = runnable;
        setDuration(duration);
    }

    public void refresh() {
        setDuration(getDuration());
    }

    public void setDuration(long duration) {
        this.duration = duration;

        startTime = TimeHelper.getCurrentMillis();
        endTime = getStartTime() + getDuration();
    }

    @Override
    public boolean isExecutionTime(long currentTime) {
        return getEndTime() <= currentTime;
    }
}
