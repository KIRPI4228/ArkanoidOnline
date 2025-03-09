package ru.arkanoid.gamebackend.models;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import ru.arkanoid.gamebackend.engine.Scale;
import ru.arkanoid.gamebackend.engine.Vector;

@Data
@SuperBuilder
public class GameObjectUpdateModel {
    private final Vector position;
    private final Scale scale;
}
