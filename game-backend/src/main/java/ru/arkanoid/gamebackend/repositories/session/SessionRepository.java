package ru.arkanoid.gamebackend.repositories.session;

import ru.arkanoid.gamebackend.session.Session;

import java.util.UUID;

public interface SessionRepository {
    Session getSessionByUUID(UUID uuid);

    void save(Session session);
}
