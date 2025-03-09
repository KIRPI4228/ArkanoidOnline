package ru.arkanoid.gamebackend.update.scheduler;

import ru.arkanoid.gamebackend.update.scheduler.tasks.SchedulerTask;

import java.util.Set;
import java.util.UUID;

public interface Scheduler {
    void addTask(SchedulerTask task);
    void removeTask(SchedulerTask task);

    boolean contains(SchedulerTask task);

    SchedulerTask getTask(UUID id);
    Set<SchedulerTask> getTasksByStartTime(long startTime);
}
