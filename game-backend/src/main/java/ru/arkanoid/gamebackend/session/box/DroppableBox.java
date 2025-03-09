package ru.arkanoid.gamebackend.session.box;

import lombok.Getter;
import lombok.Setter;
import ru.arkanoid.gamebackend.engine.Room;
import ru.arkanoid.gamebackend.engine.Vector;
import ru.arkanoid.gamebackend.models.HitBoxModel;
import ru.arkanoid.gamebackend.session.Session;
import ru.arkanoid.gamebackend.session.box.drop.Drop;
import ru.arkanoid.gamebackend.session.box.drop.DropType;

import java.util.function.Consumer;

public class DroppableBox extends StandardBox {
    private final DropType dropType;

    @Setter
    @Getter
    private Drop drop;

    public DroppableBox(Room room, Vector position, BoxType type, Consumer<HitBoxModel> hitBoxCallback, String[] arguments) {
        super(room, position, type, hitBoxCallback, arguments);
        dropType = DropType.valueOf(arguments[0]);
    }

    @Override
    public void hit(Session session) {
        drop = dropType.build(getRoom(), getPosition());

        if (drop != null) {
            getRoom().insert(drop);
        }


        super.hit(session);
    }
}
