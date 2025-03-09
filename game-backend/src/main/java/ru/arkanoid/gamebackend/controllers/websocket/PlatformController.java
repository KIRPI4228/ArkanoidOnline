package ru.arkanoid.gamebackend.controllers.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import ru.arkanoid.gamebackend.engine.Vector;
import ru.arkanoid.gamebackend.services.PlatformService;
import ru.arkanoid.gamebackend.services.SessionService;

@Controller
@RequiredArgsConstructor
public class PlatformController {
    private final PlatformService platformService;
    private final SessionService sessionService;

    @MessageMapping("/platform/move/start")
    public void startMove(SimpMessageHeaderAccessor header, Vector model) {
        float direction = model.getX();
        platformService.startMove(sessionService.extract(header), direction);
    }

    @MessageMapping("/platform/move/stop")
    public void startMove(SimpMessageHeaderAccessor header) {
        platformService.stopMove(sessionService.extract(header));
    }
}
