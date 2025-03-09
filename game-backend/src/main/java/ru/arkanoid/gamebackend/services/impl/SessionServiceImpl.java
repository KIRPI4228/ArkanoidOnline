package ru.arkanoid.gamebackend.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;
import ru.arkanoid.gamebackend.helpers.Debug;
import ru.arkanoid.gamebackend.repositories.session.SessionRepository;
import ru.arkanoid.gamebackend.services.LevelService;
import ru.arkanoid.gamebackend.services.SessionService;
import ru.arkanoid.gamebackend.services.TransactionService;
import ru.arkanoid.gamebackend.services.WebSocketService;
import ru.arkanoid.gamebackend.session.Session;
import ru.arkanoid.gamebackend.session.StandardSession;
import ru.arkanoid.gamebackend.update.FixedUpdater;
import ru.arkanoid.gamebackend.update.scheduler.Scheduler;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
@Repository
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private static final long MAX_TIME_OUT = TimeUnit.HOURS.toMillis(2);

    private final SessionRepository repository;
    private final LevelService levelService;
    private final WebSocketService webSocketService;
    private final FixedUpdater fixedUpdater;
    private final Scheduler scheduler;
    private final TransactionService transactionService;

    @Override
    public Session create(String key, String token, String ip) {
        // TODO: make key verification to main backend

        StandardSession session = new StandardSession(key, token, ip, MAX_TIME_OUT);

        try {
            session.setLevel(levelService.loadJson(
                    session,
                    fixedUpdater,
                    scheduler,
                    model -> webSocketService.send(session, "box/hit", model),
                    model -> {
                        close(session);
                        webSocketService.send(session, "session/gameover", model);

                    },
                    model -> webSocketService.send(session, "session/update", model),
                    "D:\\level1.json"
            ));
        } catch (IOException e) {
            // TODO: 28.06.2024 Make log
            throw new RuntimeException(e);
        }

        var response = transactionService.buyGame(session);

        if (!response.getIsOk()) {
            Debug.log(response.getResponse());
            // TODO: 28.06.2024 Make error sending to client
        }

        session.startExitTimer();

        repository.save(session);

        return session;
    }

    @Override
    public Session extract(SimpMessageHeaderAccessor accessor) {
        return (Session) accessor.getSessionAttributes().get("session");
    }

    @Override
    public Session extract(WebSocketSession session) {
        return (Session) session.getAttributes().get("session");
    }

    @Override
    public void close(Session session) {
        session.close();
    }
}
