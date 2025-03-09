package ru.arkanoid.gamebackend.engine;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vector {
    private float x;
    private float y;
    private float z;

    public Vector() {}

    public static Vector getMinVector(Vector pos1, Vector pos2) {
        return new Vector(Math.min(pos1.getX(), pos2.getX()), Math.min(pos1.getY(), pos2.getY()), 0);
    }

    public static Vector getMaxVector(Vector pos1, Vector pos2) {
        return new Vector(Math.max(pos1.getX(), pos2.getX()), Math.max(pos1.getY(), pos2.getY()), 0);
    }

    public Vector subtract(Vector position) {
        subtract(position.getX(), position.getY());
        return this;
    }
    public Vector subtract(float x, float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vector multiply(Vector position) {
        multiply(position.getX(), position.getY());
        return this;
    }
    public Vector multiply(float x, float y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    public Vector increase(Vector position) {
        increase(position.getX(), position.getY());
        return this;
    }
    public Vector increase(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public boolean isLess(Vector target) {
        return x < target.getX() && y < target.getY();
    }
    public boolean isLessAndEqual(Vector target) {
        return x <= target.getX() && y <= target.getY();
    }

    public boolean isMore(Vector target) {
        return x > target.getX() && y > target.getY();
    }

    public boolean isMoreAndEqual(Vector target) {
        return x >= target.getX() && y >= target.getY();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Vector model
                && model.x == this.x
                && model.y == this.y
                && model.z == this.z;
    }

    @Override
    public Vector clone() {
        return new Vector(x, y, z);
    }

    @Override
    public String toString() {
        return "[x: " + x + ", y: " + y + "]";
    }
}
