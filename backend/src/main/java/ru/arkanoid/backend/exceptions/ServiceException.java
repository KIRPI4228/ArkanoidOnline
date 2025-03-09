package ru.arkanoid.backend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceException extends RuntimeException {
    private int code = 400;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException() {
    }
}
