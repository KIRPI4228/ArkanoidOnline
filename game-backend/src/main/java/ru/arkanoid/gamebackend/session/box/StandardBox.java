package ru.arkanoid.gamebackend.session.box;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.arkanoid.gamebackend.engine.Room;
import ru.arkanoid.gamebackend.engine.Vector;
import ru.arkanoid.gamebackend.models.HitBoxModel;

import java.util.UUID;
import java.util.function.Consumer;

@Getter
@RequiredArgsConstructor
public class StandardBox implements Box {
    private final UUID id = UUID.randomUUID();
    private final Room room;
    private final Vector position;
    private final BoxType type;
    private final Consumer<HitBoxModel> hitBoxCallback;
    private final String[] arguments;
    @Setter
    private String color = "BLUE";
    @Setter
    private int prize = 10;
}
