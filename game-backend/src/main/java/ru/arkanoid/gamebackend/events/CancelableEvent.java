package ru.arkanoid.gamebackend.events;

public interface CancelableEvent extends Event {
    boolean isCanceled();
    void setCanceled(boolean state);
}
