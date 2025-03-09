package ru.arkanoid.gamebackend.session.levels;

import lombok.Builder;
import lombok.Data;
import ru.arkanoid.gamebackend.engine.Room;
import ru.arkanoid.gamebackend.engine.Scale;
import ru.arkanoid.gamebackend.engine.Vector;
import ru.arkanoid.gamebackend.models.GameOverModel;
import ru.arkanoid.gamebackend.models.HitBoxModel;
import ru.arkanoid.gamebackend.models.SessionUpdateModel;
import ru.arkanoid.gamebackend.session.Session;
import ru.arkanoid.gamebackend.session.ball.StandardBall;
import ru.arkanoid.gamebackend.session.box.Box;
import ru.arkanoid.gamebackend.session.box.BoxType;
import ru.arkanoid.gamebackend.session.platform.StandardPlatform;
import ru.arkanoid.gamebackend.update.Updater;
import ru.arkanoid.gamebackend.update.scheduler.Scheduler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

@Data
@Builder
public class LevelMapper {
    private final List<List<String>> lines;

    private final Vector gap;

    private final float platformSize;
    private final float platformSpeed;
    private final float ballSpeed;
    private final float ballRadius;


    public Level generateLevel(Session session,
                               Updater updater,
                               Scheduler scheduler,
                               Consumer<HitBoxModel> hitBoxCallback,
                               Consumer<GameOverModel> gameOverCallback,
                               Consumer<SessionUpdateModel> sessionUpdateCallback) {
        var level = new Level(updater, scheduler, sessionUpdateCallback, gameOverCallback);

        var platform = new StandardPlatform(level);
        platform.setScale(new Scale(platformSize, platform.getScale().getY()));
        platform.setSpeed(platformSpeed);

        var ball = new StandardBall(level, session);
        ball.setSpeed(ballSpeed);
        ball.setScale(new Scale(ballRadius * 2, 0));

        level.setBall(ball);
        level.setPlatform(platform);

        level.setBoxes(generateBoxes(level, hitBoxCallback));

        return level;
    }

    public Set<Box> generateBoxes(Room room, Consumer<HitBoxModel> hitBoxCallback) {
        Set<Box> boxes = new HashSet<>();

        var y = room.getHeight() / 2 - (gap.getY() + 1);
        for (var line : lines) {
            var x = -(room.getWidth() / 2) + gap.getX() + 1;
            for (var boxText : line) {
                try {
                    var sections = boxText.split(";");

                    var prize = sections[1];
                    var color = sections[2];

                    var arguments = sections[0].split("<");

                    var type = arguments[0];

                    if (arguments.length >= 2) {
                        arguments = arguments[1].replaceAll(">", "").split(",");
                    } else {
                        arguments = new String[0];
                    }




                    var box = BoxType.valueOf(type).build(room, new Vector(x, y, 0), hitBoxCallback, arguments);
                    box.setPrize(Integer.parseInt(prize));
                    box.setColor(color);

                    boxes.add(box);
                } catch (Exception e) {
                    // Ignore
                }

                x += Box.SCALE.getX();
            }
            y -= Box.SCALE.getY();
        }

        return boxes;
    }
}
