package ru.arkanoid.gamebackend.models;

import lombok.Builder;
import lombok.Data;
import ru.arkanoid.gamebackend.engine.Scale;
import ru.arkanoid.gamebackend.engine.Vector;

@Data
@Builder
public class LevelModel {
    private Vector[] boxesPositions;
    private Scale[] boxesScales;
    private String[] boxesIds;
    private String[] boxesTypes;
    private String[] boxesColors;
    private Vector platformPosition;
    private Vector ballPosition;
    private Scale platformScale;
    private float ballRadius;
    private float width;
    private float height;
}
