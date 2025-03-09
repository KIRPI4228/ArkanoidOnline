package ru.arkanoid.gamebackend.update.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.arkanoid.gamebackend.helpers.CollectionsHelper;
import ru.arkanoid.gamebackend.helpers.TimeHelper;
import ru.arkanoid.gamebackend.update.Updater;
import ru.arkanoid.gamebackend.update.scheduler.tasks.SchedulerTask;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class SchedulerImpl implements Scheduler {

    private final Updater updater;

    private final Set<SchedulerTask> tasks = CollectionsHelper.createConcurrentSet();

    @PostConstruct
    private void init() {
        updater.addExecutor(this, this::update);
    }

    private void update() {
        var currentTime = TimeHelper.getCurrentMillis();

        tasks.forEach(task -> {
                    if (task.isExecutionTime(currentTime)) {
                        task.execute(this);
                    }
                });
    }

    @Override
    public void addTask(SchedulerTask task) {
        tasks.add(task);
    }

    @Override
    public void removeTask(SchedulerTask task) {
        tasks.remove(task);
    }

    @Override
    public boolean contains(SchedulerTask task) {
        return tasks.contains(task);
    }

    @Override
    public SchedulerTask getTask(UUID id) {
        return tasks.stream()
                .filter(task -> id.equals(task.getId()))
                .findFirst().orElseThrow();
    }

    @Override
    public Set<SchedulerTask> getTasksByStartTime(long startTime) {
        return tasks.stream()
                .filter(task -> startTime == task.getStartTime())
                .collect(Collectors.toUnmodifiableSet());
    }
}
