package ru.arkanoid.backend.filters;

import ru.arkanoid.backend.exceptions.ServiceException;

public final class FilterActions {
    public static void ThrowException(String error) {
        throw new ServiceException(error);
    }
}
