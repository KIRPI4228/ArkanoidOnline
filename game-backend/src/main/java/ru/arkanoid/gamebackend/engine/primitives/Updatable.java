package ru.arkanoid.gamebackend.engine.primitives;

import java.util.function.Consumer;

public interface Updatable<T> {
    Consumer<T> getUpdater();
}
