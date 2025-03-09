package ru.arkanoid.gamebackend.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BooleanException extends RuntimeException {
    private final boolean state;

    public boolean getState() {
        return state;
    }
}
