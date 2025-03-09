package ru.arkanoid.gamebackend.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import ru.arkanoid.gamebackend.services.SessionService;
import ru.arkanoid.gamebackend.session.Session;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class HeaderHandshakeInterceptor implements HandshakeInterceptor {
    private final SessionService sessionService;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        attributes.put("ip", request.getRemoteAddress());

        String[] sectors = request.getHeaders().getFirst("Sec-WebSocket-Protocol").replaceAll(" ", "").split(",");

        String key = sectors[2];
        String token = sectors[1];
        String ip = request.getRemoteAddress().toString();

        Session session = sessionService.create(key, token, ip);

        attributes.put("session", session);

        return session != null;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }


}
