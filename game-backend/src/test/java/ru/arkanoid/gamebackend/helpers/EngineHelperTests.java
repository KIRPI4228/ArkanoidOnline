package ru.arkanoid.gamebackend.helpers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.arkanoid.gamebackend.engine.Scale;
import ru.arkanoid.gamebackend.engine.Vector;
import ru.arkanoid.gamebackend.engine.primitives.circle.CircleCollision;
import ru.arkanoid.gamebackend.instances.engine.primitives.CircleTestInstance;
import ru.arkanoid.gamebackend.instances.engine.primitives.RectangleTestInstance;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EngineHelperTests {

    @Test
    void getRectangleSideFromCircleCollisionTest() {

        var rectangle = new RectangleTestInstance(null, new Vector(0, 0, 0), new Scale(1, 1));

        Map<Vector, CircleCollision> objects = new HashMap() {{
            put(
                    new Vector(0, 1, 0),
                    new CircleTestInstance(null, new Vector(0, 1.3f, 0), new Scale(2, 0))
            );

            put(
                    new Vector(1, 0, 0),
                    new CircleTestInstance(null, new Vector(1.3f, 0, 0), new Scale(2, 0))
            );

            put(
                    new Vector(0, -1, 0),
                    new CircleTestInstance(null, new Vector(0, -1.3f, 0), new Scale(2, 0))
            );

            put(
                    new Vector(-1, 0, 0),
                    new CircleTestInstance(null, new Vector(-1.3f, 0, 0), new Scale(2, 0))
            );

            put(
                    new Vector(1, 1, 0),
                    new CircleTestInstance(null, new Vector(1.3f, 1.3f, 0), new Scale(2, 0))
            );

            put(
                    new Vector(-1, -1, 0),
                    new CircleTestInstance(null, new Vector(-1.3f, -1.3f, 0), new Scale(2, 0))
            );

            put(
                    new Vector(1, -1, 0),
                    new CircleTestInstance(null, new Vector(1.3f, -1.3f, 0), new Scale(2, 0))
            );

            put(
                    new Vector(-1, 1, 0),
                    new CircleTestInstance(null, new Vector(-1.3f, 1.3f, 0), new Scale(2, 0))
            );

            put(
                    new Vector(0, 0, 0),
                    new CircleTestInstance(null, new Vector(2.3f, 2.3f, 0), new Scale(2, 0))
            );
        }};

        objects.keySet().stream()
                .forEach(expectedSide -> {
                    var circle = objects.get(expectedSide);

                    var actualSide = EngineHelper.getRectangleSideFromCircleCollision(rectangle, circle);

                    assertEquals(expectedSide, actualSide, "Collision side musts be " + expectedSide + " when circle coordinates is " + circle.getPosition() + " and radius is " + circle.getRadius());
                });
    }

    @Test
    void doesRectangleCollideRectangle() {
        var rectangle1 = new RectangleTestInstance(null, new Vector(0, 0, 0), new Scale(1, 1));
        var rectangle2 = new RectangleTestInstance(null, new Vector(0.6f, 0.7f, 0), new Scale(1, 1));

        var doesCollide = EngineHelper.doesRectangleCollideRectangle(rectangle1, rectangle2);

        assertTrue(doesCollide, "Rectangle with position - " + rectangle1.getPosition() + " and scale - " + rectangle1.getScale() + " must collide with another rectangle with position - " + rectangle2.getPosition() + " and scale - " + rectangle2.getScale());


        rectangle2 = new RectangleTestInstance(null, new Vector(2f, 1.7f, 0), new Scale(1, 1));

        doesCollide = EngineHelper.doesRectangleCollideRectangle(rectangle1, rectangle2);

        assertFalse(doesCollide, "Rectangle with position - " + rectangle1.getPosition() + " and scale - " + rectangle1.getScale() + " must not collide with another rectangle with position - " + rectangle2.getPosition() + " and scale - " + rectangle2.getScale());
    }

    @Test
    void isPointInRectangleTest() {
        var rectangle = new RectangleTestInstance(null, new Vector(0, 0, 0), new Scale(1, 1));

        var point = new Vector(0.3f, 0.4f, 0);

        var isInRectangle = EngineHelper.isPointInRectangle(point, rectangle.getBeginPosition(), rectangle.getEndPosition());

        assertTrue(isInRectangle, "Point with position - " + point + " must be inside begin coordinate - " + rectangle.getBeginPosition() + " and end coordinate - " + rectangle.getEndPosition());


        point = new Vector(1.3f, 1.9f, 0);

        isInRectangle = EngineHelper.isPointInRectangle(point, rectangle.getBeginPosition(), rectangle.getEndPosition());

        assertFalse(isInRectangle, "Point with position - " + point + " must not be inside begin coordinate - " + rectangle.getBeginPosition() + " and end coordinate - " + rectangle.getEndPosition());
    }
}
