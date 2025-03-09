package ru.arkanoid.gamebackend.repositories.session.impl;

import org.springframework.stereotype.Repository;
import ru.arkanoid.gamebackend.repositories.session.SessionRepository;
import ru.arkanoid.gamebackend.session.Session;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class InMemorySessionRepository implements SessionRepository {
    private final Map<UUID, Session> sessions = new HashMap<>();

    @Override
    public Session getSessionByUUID(UUID uuid) {
        return sessions.get(uuid);
    }

    @Override
    public void save(Session session) {
        if (sessions.containsKey(session.getUuid())) {
            sessions.replace(session.getUuid(), session);
        } else {
            sessions.put(session.getUuid(), session);
        }
    }
}
