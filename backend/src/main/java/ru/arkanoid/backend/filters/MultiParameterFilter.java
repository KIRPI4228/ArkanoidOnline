package ru.arkanoid.backend.filters;

import java.util.function.Consumer;

public interface MultiParameterFilter<T, U> {
    String doFilter(T firstParameter, U secondParameter);

    default void doFilter(T firstParameter, U secondParameter, Consumer<String> consumer) {
        String error = doFilter(firstParameter, secondParameter);
        if (error != null) {
            consumer.accept(error);
        }
    }
}
