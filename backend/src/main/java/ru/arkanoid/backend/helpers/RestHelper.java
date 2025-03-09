package ru.arkanoid.backend.helpers;

import org.springframework.http.ResponseEntity;
import ru.arkanoid.backend.exceptions.ServiceException;

import java.util.Optional;
import java.util.function.Supplier;

public final class RestHelper {
    public static <T> ResponseEntity getResponse(Supplier<T> supplier) {
        try {
            return ResponseEntity.ok(Optional.of(supplier.get()).orElseThrow(() -> new ServiceException()));
        } catch (ServiceException exception) {
            return ResponseEntity.status(exception.getCode()).body(exception.getMessage());
        }
    }

    public static <T> ResponseEntity getResponse(Runnable runnable) {
        return getResponse(() -> {
            runnable.run();
            return ResponseEntity.ok().build();
        });
    }
}
