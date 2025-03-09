package ru.arkanoid.gamebackend.engine.primitives.line;

import lombok.Getter;
import ru.arkanoid.gamebackend.engine.Vector;
import ru.arkanoid.gamebackend.helpers.MathHelper;

@Getter
public class SimpleLine implements Line {
    private Vector firstPosition;
    private Vector secondPosition;
    private Vector direction;

    private float angle;
    private float distance;

    public SimpleLine(Vector firstPosition, float angle, float distance) {
        this.firstPosition = firstPosition;
        this.angle = angle;
        this.distance = distance;

        secondPosition = new Vector(
                firstPosition.getX() - distance * MathHelper.cos(angle),
                firstPosition.getY() - distance * MathHelper.sin(angle),
                0
        );

        direction = new Vector(
                MathHelper.cos(angle),
                MathHelper.sin(angle),
                0
        );
    }

    public static SimpleLine createLine(Vector firstPosition, Vector secondPosition) {
        float xDelta = firstPosition.getX() - secondPosition.getX();
        float yDelta = firstPosition.getY() - secondPosition.getY();
        return new SimpleLine(
                firstPosition,
                MathHelper.getAngle(xDelta, yDelta),
                MathHelper.getDistance(xDelta, yDelta)
        );
    }
}
