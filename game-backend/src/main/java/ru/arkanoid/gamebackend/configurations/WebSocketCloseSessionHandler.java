package ru.arkanoid.gamebackend.configurations;

import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.arkanoid.gamebackend.services.SessionService;

@Component
@RequiredArgsConstructor
public class WebSocketCloseSessionHandler extends TextWebSocketHandler {
    private final SessionService sessionService;

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        LoggerFactory.getLogger("1").info("123");
        sessionService.close(sessionService.extract(session));
    }
}
