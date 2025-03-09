package ru.arkanoid.gamebackend.controllers.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import ru.arkanoid.gamebackend.services.BallService;
import ru.arkanoid.gamebackend.services.SessionService;

@Controller
@RequiredArgsConstructor
public class BallController {
    private final BallService ballService;
    private final SessionService sessionService;

    @MessageMapping("/ball/start")
    public void start(SimpMessageHeaderAccessor header) {
        ballService.start(sessionService.extract(header));
    }
}
