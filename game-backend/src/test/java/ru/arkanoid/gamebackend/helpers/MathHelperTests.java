package ru.arkanoid.gamebackend.helpers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.arkanoid.gamebackend.engine.Vector;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MathHelperTests {
    @Test
    void getIntegerFromBooleanTest() {
        var integer = MathHelper.getIntegerFromBoolean(true);

        assertEquals(1, integer, "Integer must be 1 when true");

        integer = MathHelper.getIntegerFromBoolean(false);

        assertEquals(0, integer, "Integer must be 0 when false");
    }

    @Test
    void sinTest() {
        var value = MathHelper.sin(0);

        assertEquals(0, value, "Value must be 0 when sin is 0");

        value = MathHelper.sin(90);

        assertEquals(1, value, "Value must be 1 when sin is 90");

        value = MathHelper.sin(180);

        assertEquals(0, value, "Value must be 0 when sin is 180");
    }

    @Test
    void cosTest() {
        var value = MathHelper.cos(0);

        assertEquals(1, value, "Value must be 1 when cos is 0");

        value = MathHelper.cos(180);

        assertEquals(-1, value, "Value must be -1 when cos is 180");
    }

    @Test
    void getAngleTest() {
        Map<Float, Vector> directions = new HashMap() {{
            put(0f, new Vector(0, 0, 0));
            put(0f, new Vector(1, 0, 0));
            put(45f, new Vector(1, 1, 0));
            put(90f, new Vector(0, 1, 0));
            put(180f, new Vector(-1, 0, 0));
            put(-135f, new Vector(-1, -1, 0));
            put(-90f, new Vector(0, -1, 0));
        }};


        directions.keySet().stream()
                .forEach(expectedAngle -> {
                    var direction = directions.get(expectedAngle);

                    var actualAngle = MathHelper.getAngle(direction);

                    assertEquals(expectedAngle, actualAngle, "Angle must be " + expectedAngle + " when coordinates is " + direction);
                });
    }
}
