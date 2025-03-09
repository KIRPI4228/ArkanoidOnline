package ru.arkanoid.gamebackend.helpers;


import ru.arkanoid.gamebackend.engine.Vector;

public final class MathHelper {
    public static float sin(float value) {
        return (float) Math.round(Math.sin(value * Math.PI / 180.0) * 10000) * 0.0001f;
    }

    public static float cos(float value) {
        return (float) Math.round(Math.cos(value * Math.PI / 180.0) * 10000) * 0.0001f;
    }

    public static float getDistance(float x1, float y1, float x2, float y2) {
        return getDistance(x1 - x2, y1 - y2);
    }

    public static float getDistance(Vector position1, Vector position2) {
        // TODO: 15.06.2024 Make tests
        return getDistance(position1.clone().subtract(position2));
    }

    public static float getDistance(float deltaX, float deltaY) {
        return (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    public static float getDistance(Vector delta) {
        return getDistance(delta.getX(), delta.getY());
    }

    public static float getAngle(float x, float y) {
        return (float) (Math.atan2(y, x) * (180.0f / Math.PI));
    }

    public static float getAngle(Vector direction) {
        return getAngle(direction.getX(), direction.getY());
    }

    public static float getDirectionMultiplier(float target) {
        return target == 0 ? 1 : -1;
    }

    public static int getIntegerFromBoolean(boolean state) {
        return 1 & (Boolean.hashCode(state) >> 1);
    }

    public static Vector getDirection(Vector positivePosition, Vector negativePosition) {
        var multiplierX = Math.signum(negativePosition.getX() - positivePosition.getX());
        var multiplierY = Math.signum(negativePosition.getY() - positivePosition.getY());
        var minX = Math.min(negativePosition.getX(), positivePosition.getX());
        var minY = Math.min(negativePosition.getY(), positivePosition.getY());
        return new Vector(multiplierX * getIntegerFromBoolean(minX < minY), multiplierY * getIntegerFromBoolean(minY < minX), 0);
    }
}
