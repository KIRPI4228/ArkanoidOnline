package ru.arkanoid.gamebackend.services;

import ru.arkanoid.gamebackend.session.Session;

public interface WebSocketService {
    void send(String key, String destination, Object packet);

    default void send(Session session, String destination, Object packet) {
        send(session.getKey(), destination, packet);
    }
}
