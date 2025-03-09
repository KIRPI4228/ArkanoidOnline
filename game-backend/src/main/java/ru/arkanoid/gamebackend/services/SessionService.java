package ru.arkanoid.gamebackend.services;

import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.socket.WebSocketSession;
import ru.arkanoid.gamebackend.session.Session;

public interface SessionService {
    Session create(String key, String token, String ip);
    Session extract(SimpMessageHeaderAccessor accessor);
    Session extract(WebSocketSession session);

    void close(Session session);
}
