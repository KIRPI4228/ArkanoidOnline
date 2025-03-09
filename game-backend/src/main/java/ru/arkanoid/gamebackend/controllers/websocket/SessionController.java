package ru.arkanoid.gamebackend.controllers.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import ru.arkanoid.gamebackend.models.LevelModel;
import ru.arkanoid.gamebackend.services.SessionService;
import ru.arkanoid.gamebackend.services.WebSocketService;
import ru.arkanoid.gamebackend.session.Session;

@Controller
@RequiredArgsConstructor
public class SessionController {
    private final SessionService sessionService;
    private final WebSocketService webSocketService;

    @MessageMapping("/level/get")
    public void map(SimpMessageHeaderAccessor header) {
        var session = sessionService.extract(header);
        sendLevel(session, session.getLevel().toModel());
    }

    @MessageMapping("/session/close")
    public void close(SimpMessageHeaderAccessor header) {
        var session = sessionService.extract(header);
        sessionService.close(session);
    }

    private void sendLevel(Session session, LevelModel model) {
        webSocketService.send(session, "level", model);
    }
}
