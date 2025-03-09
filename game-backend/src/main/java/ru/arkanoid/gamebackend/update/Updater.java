package ru.arkanoid.gamebackend.update;

import java.util.*;

public abstract class Updater {
    private final Map<Object, UpdaterExecutor> executors = Collections.synchronizedMap(new HashMap<>());

    public synchronized void addExecutor(Object key, UpdaterExecutor executor) {
        executors.put(key, executor);
    }

    public synchronized void removeExecutor(Object key) {
        executors.remove(key);
    }

    protected synchronized void dispose() {
        executors.values().stream().forEach(executor -> execute(executor));
    }

    protected abstract void execute(UpdaterExecutor executor);

    public abstract void start();
    public abstract void stop();
}
