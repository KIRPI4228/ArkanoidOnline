package ru.arkanoid.gamebackend.models;

import lombok.Builder;
import lombok.Data;
import ru.arkanoid.gamebackend.engine.Vector;

@Builder
@Data
public class LineMoveModel {
    private Vector position;
    private Vector direction;
    private float speed;
}
