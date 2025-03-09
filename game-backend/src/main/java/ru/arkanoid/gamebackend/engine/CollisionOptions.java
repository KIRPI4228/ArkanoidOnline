package ru.arkanoid.gamebackend.engine;

import lombok.Builder;
import lombok.Getter;
import ru.arkanoid.gamebackend.engine.primitives.Collider;

@Getter
@Builder
public class CollisionOptions {
    private Vector direction;
    private Collider colliding;
}
