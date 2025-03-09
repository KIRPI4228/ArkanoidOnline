package ru.arkanoid.gamebackend.session;

import ru.arkanoid.gamebackend.session.levels.Level;

import java.util.UUID;

public interface Session {
    String getKey();
    String getToken();
    String getIp();
    UUID getUuid();
    Level getLevel();
    int getScore();
    void addScore(int amount);
    void close();
    void startExitTimer();
}
