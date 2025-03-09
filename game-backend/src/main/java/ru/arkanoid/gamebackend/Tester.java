package ru.arkanoid.gamebackend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.arkanoid.gamebackend.services.BallService;
import ru.arkanoid.gamebackend.services.PlatformService;
import ru.arkanoid.gamebackend.services.SessionService;
import ru.arkanoid.gamebackend.session.Session;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Tester {
    // TODO: 14.06.2024 Move that code to test class
    
    private final SessionService sessionService;
    private final PlatformService platformService;
    private final BallService ballService;

    public void test(int players) {
        for (int i = 0; i < players; i++) {
            Session session = sessionService.create(UUID.randomUUID().toString(), UUID.randomUUID().toString(), "sdf");


            ballService.start(session);
            platformService.startMove(session, 1);
        }
    }

    @PostConstruct
    private void init() {
        //test(2750);

        /*Room room = new Room();

        RectangleGameObject square1 = new StandardPlatform(room);
        RectangleGameObject square2 = new StandardPlatform(room);
        square2.getPosition().setY(0);
        square2.getPosition().setX(0);
        CircleGameObject circle = new StandardBall(room);
        circle.getPosition().setX(0);
        circle.getPosition().setY(-0.2f);

        LoggerFactory.getLogger("test").info(room.findCollidedGameObject(circle).getDirection() + "");*/
        //LoggerFactory.getLogger("test").info(room.findGameObjectFromPoint(new PositionModel(-2.2f, 0.3f, 0)) + "");
    }
}
