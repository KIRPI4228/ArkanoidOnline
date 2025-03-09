package ru.arkanoid.gamebackend.session.levels;

import lombok.Getter;
import ru.arkanoid.gamebackend.engine.Room;
import ru.arkanoid.gamebackend.engine.Scale;
import ru.arkanoid.gamebackend.engine.Vector;
import ru.arkanoid.gamebackend.models.*;
import ru.arkanoid.gamebackend.session.ball.Ball;
import ru.arkanoid.gamebackend.session.box.Box;
import ru.arkanoid.gamebackend.session.box.DroppableBox;
import ru.arkanoid.gamebackend.session.platform.Platform;
import ru.arkanoid.gamebackend.update.Updater;
import ru.arkanoid.gamebackend.update.scheduler.Scheduler;

import java.util.Set;
import java.util.function.Consumer;

@Getter
public class Level extends Room {
    private Set<Box> boxes;
    private Platform platform;
    private Ball ball;
    private GameOverTrigger gameOverTrigger;

    private final Consumer<SessionUpdateModel> sessionUpdater;

    public Level(Updater updater, Scheduler scheduler, Consumer<SessionUpdateModel> sessionUpdater, Consumer<GameOverModel> gameOverCallback) {
        super(updater, scheduler);
        this.sessionUpdater = sessionUpdater;

        gameOverTrigger = new GameOverTrigger(this, gameOverCallback);

        insert(gameOverTrigger);
    }

    public void setBoxes(Set<Box> boxes) {
        this.boxes = boxes;
        boxes.stream().forEach(this::insert);
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
        insert(platform);
    }

    public void setBall(Ball ball) {
        this.ball = ball;
        insert(ball);
    }

    @Override
    protected void update() {
        super.update();

        sessionUpdater.accept(SessionUpdateModel.builder()
                .ball(GameObjectUpdateModel.builder()
                        .position(ball.getPosition())
                        .scale(ball.getScale())
                        .build())

                .platform(GameObjectUpdateModel.builder()
                        .position(platform.getPosition())
                        .scale(platform.getScale())
                        .build())

                .drops(getBoxes().stream()
                        .map(box -> box instanceof DroppableBox drop ? drop : null) // TODO: 28.06.2024 Try to remove if else here
                        .filter(box -> box != null && box.getDrop() != null)
                        .map(box -> DropGameObjectUpdateModel.builder()
                                .id(box.getId().toString())
                                .type(box.getDrop().getType().name())
                                .scale(box.getDrop().getScale())
                                .position(box.getDrop().getPosition())
                                .build())
                        .toArray(DropGameObjectUpdateModel[]::new))

                .build());
    }

    public LevelModel toModel() {
        return LevelModel.builder()
                .boxesPositions(boxes.stream().map(Box::getPosition).toArray(Vector[]::new))
                .boxesScales(boxes.stream().map(Box::getScale).toArray(Scale[]::new))
                .boxesTypes(boxes.stream().map(box -> box.getType().name()).toArray(String[]::new))
                .boxesColors(boxes.stream().map(Box::getColor).toArray(String[]::new))
                .boxesIds(boxes.stream().map(box -> box.getId().toString()).toArray(String[]::new))

                .ballPosition(ball.getPosition())
                .ballRadius(ball.getRadius())

                .platformPosition(platform.getPosition())
                .platformScale(platform.getScale())

                .width(getWidth())
                .height(getHeight())

                .build();
    }
}
