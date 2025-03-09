package ru.arkanoid.gamebackend.session.box;

import ru.arkanoid.gamebackend.engine.primitives.rectangle.RectangleCollision;
import ru.arkanoid.gamebackend.engine.primitives.rectangle.RectangleGameObject;
import ru.arkanoid.gamebackend.engine.Scale;
import ru.arkanoid.gamebackend.models.HitBoxModel;
import ru.arkanoid.gamebackend.session.Session;

import java.util.UUID;
import java.util.function.Consumer;

public interface Box extends RectangleGameObject, RectangleCollision {
    Scale SCALE = new Scale(1, 1);

    int getPrize();
    UUID getId();
    BoxType getType();
    String getColor();
    Consumer<HitBoxModel> getHitBoxCallback();

    void setPrize(int prize);
    void setColor(String color);

    @Override
    default Scale getScale() {
        return SCALE;
    }

    @Override
    @Deprecated
    default void setScale(Scale scale) { }

    default void hit(Session session) {
        session.addScore(getPrize());
        destroy();
        getHitBoxCallback().accept(HitBoxModel.builder()
                .id(getId().toString())
                .score(session.getScore())
                .build());
    }
}
