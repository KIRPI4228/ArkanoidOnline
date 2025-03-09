package ru.arkanoid.gamebackend.session.levels;

import lombok.Getter;
import lombok.Setter;
import ru.arkanoid.gamebackend.engine.Scale;
import ru.arkanoid.gamebackend.engine.Vector;
import ru.arkanoid.gamebackend.engine.primitives.rectangle.RectangleCollision;
import ru.arkanoid.gamebackend.engine.primitives.rectangle.RectangleGameObject;
import ru.arkanoid.gamebackend.models.GameOverModel;

import java.util.function.Consumer;

@Getter
public class GameOverTrigger implements RectangleGameObject, RectangleCollision {
    private Level room;
    private Consumer<GameOverModel> gameOverCallback;

    @Setter
    private Vector position;

    @Setter
    private Scale scale;

    public GameOverTrigger(Level level, Consumer<GameOverModel> gameOverCallback) {
        this.room = level;
        this.gameOverCallback = gameOverCallback;

        position = new Vector(0, level.getHeight() / 2 * -1, 0);
        scale = new Scale(level.getWidth(), 1);
    }

    public void gameOver() {
        gameOverCallback.accept(GameOverModel.builder()
                .state(true)
                .build());
    }
}
