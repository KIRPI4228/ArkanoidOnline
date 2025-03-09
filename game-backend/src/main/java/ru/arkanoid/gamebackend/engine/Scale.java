package ru.arkanoid.gamebackend.engine;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Scale {
    private float x;
    private float y;

    public Scale() { }

    @Override
    public String toString() {
        return "[x: " + x + ", y: " + y + "]";
    }
}
