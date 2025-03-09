package ru.arkanoid.gamebackend.helpers;

import ru.arkanoid.gamebackend.engine.Vector;
import ru.arkanoid.gamebackend.engine.primitives.circle.CircleCollision;
import ru.arkanoid.gamebackend.engine.primitives.rectangle.RectangleCollision;

public final class EngineHelper {

    public static Vector getRectangleSideFromCircleCollision(RectangleCollision rectangle, CircleCollision circle) {
        var begin = rectangle.getBeginPosition();
        var end = rectangle.getEndPosition();

        var range = circle.getRadius();
        var cornerRange = range - range / 8;
        

        return new Vector(
                // Right side
                MathHelper.getIntegerFromBoolean(isPointInRectangle(circle.getPosition(),
                        end.clone().subtract(0, cornerRange),
                        end.clone().increase(range, rectangle.getScale().getY() + cornerRange)))
                        -
                        // Left side
                        MathHelper.getIntegerFromBoolean(isPointInRectangle(circle.getPosition(),
                        begin.clone().increase(0, cornerRange),
                        begin.clone().subtract(range, rectangle.getScale().getY() + cornerRange))),

                // Top side
                MathHelper.getIntegerFromBoolean(isPointInRectangle(circle.getPosition(),
                        begin.clone().subtract(cornerRange, 0),
                        begin.clone().increase(rectangle.getScale().getX() + cornerRange, range)))
                        -
                        // Bottom side
                        MathHelper.getIntegerFromBoolean(isPointInRectangle(circle.getPosition(),
                        end.clone().increase(cornerRange, 0),
                        end.clone().subtract(rectangle.getScale().getX() + cornerRange, range))),
                0
        );
    }



    public static boolean doesRectangleCollideRectangle(RectangleCollision rectangle1, RectangleCollision rectangle2) {
        var begin1 = rectangle1.getBeginPosition();
        var end1 = rectangle1.getEndPosition();
        var begin2 = rectangle2.getBeginPosition();
        var end2 = rectangle2.getEndPosition();

        return end1.getX() >= begin2.getX() && begin1.getX() <= end2.getX()
                && end1.getY() <= begin2.getY() && begin1.getY() >= end2.getY();
    }

    public static boolean isPointInRectangle(Vector target, Vector pos1, Vector pos2) {
        return Vector.getMinVector(pos1, pos2).isLess(target) && Vector.getMaxVector(pos1, pos2).isMore(target);
    }
}
