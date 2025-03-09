package ru.arkanoid.gamebackend.update.scheduler.tasks;

import ru.arkanoid.gamebackend.update.scheduler.Scheduler;

import java.util.UUID;

public interface SchedulerTask {
    UUID getId();
    Runnable getRunnable();

    long getStartTime();

    boolean isExecutionTime(long currentTime);

    default void execute(Scheduler scheduler) {
        getRunnable().run();
        scheduler.removeTask(this);
    }
}
