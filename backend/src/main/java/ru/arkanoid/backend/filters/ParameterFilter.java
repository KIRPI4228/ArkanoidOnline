package ru.arkanoid.backend.filters;

import java.util.function.Consumer;

public interface ParameterFilter<T> {
    String doFilter(T parameter);

    default void doFilter(T parameter, Consumer<String> consumer) {
        String error = doFilter(parameter);
        if (error != null) {
            consumer.accept(error);
        }
    }
}
