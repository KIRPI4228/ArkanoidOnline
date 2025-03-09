package ru.arkanoid.gamebackend.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.arkanoid.gamebackend.services.WebSocketService;

@Service
@RequiredArgsConstructor
public class WebSocketServiceImpl implements WebSocketService {
    private SimpMessagingTemplate template;

    @Autowired
    public WebSocketServiceImpl(@Lazy SimpMessagingTemplate template) {
        this.template = template;
    }

    @Override
    public void send(String key, String destination, Object packet) {
        template.convertAndSend("/topic/" + key + "/" + destination, packet);
    }
}
