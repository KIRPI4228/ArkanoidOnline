package ru.arkanoid.gamebackend.services;

import ru.arkanoid.gamebackend.session.Session;

public interface PlatformService {
    void startMove(Session session, float direction);
    void stopMove(Session session);
}
