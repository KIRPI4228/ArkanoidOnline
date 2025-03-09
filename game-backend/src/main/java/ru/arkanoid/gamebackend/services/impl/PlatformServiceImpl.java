package ru.arkanoid.gamebackend.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.arkanoid.gamebackend.services.PlatformService;
import ru.arkanoid.gamebackend.session.Session;

@Service
@RequiredArgsConstructor
public class PlatformServiceImpl implements PlatformService {
    @Override
    public void startMove(Session session, float direction) {
        session.getLevel().getPlatform().start(direction);
    }

    @Override
    public void stopMove(Session session) {
        session.getLevel().getPlatform().stop();
    }
}
