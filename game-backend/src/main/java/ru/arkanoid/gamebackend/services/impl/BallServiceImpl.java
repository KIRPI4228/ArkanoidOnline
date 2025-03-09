package ru.arkanoid.gamebackend.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.arkanoid.gamebackend.services.BallService;
import ru.arkanoid.gamebackend.session.Session;

@Service
@RequiredArgsConstructor
public class BallServiceImpl implements BallService {

    @Override
    public void start(Session session) {
        session.getLevel().getBall().start();
    }
}
