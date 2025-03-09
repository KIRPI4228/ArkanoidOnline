package ru.arkanoid.gamebackend.session.box;

import ru.arkanoid.gamebackend.engine.Room;
import ru.arkanoid.gamebackend.engine.Vector;
import ru.arkanoid.gamebackend.models.HitBoxModel;

import java.util.function.Consumer;

public enum BoxType {
    DEFAULT(StandardBox::new),
    DROP(DroppableBox::new);

    private BoxBuilder builder;

    BoxType(BoxBuilder builder) {
        this.builder = builder;
    }

    public Box build(Room room, Vector position, Consumer<HitBoxModel> hitBoxCallback, String[] arguments) {
        return builder.build(room, position, this, hitBoxCallback, arguments);
    }

    public interface BoxBuilder {
        Box build(Room room, Vector position, BoxType type, Consumer<HitBoxModel> hitBoxCallback, String[] arguments);
    }
}
