package ru.arkanoid.backend.repositories;;

import lombok.Setter;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;

public class RepositoryContainer<R> extends ArrayList<R> {
    @Setter
    private R mainRepository;

    public R getRepository() {
        return mainRepository;
    }

    public <E> E getValue(Function<R, E> function) {
        for (R repository : this) {
            E element = function.apply(repository);
            if (element != null) {
                return element;
            }
        }

        return null;
    }

    public R getRepository(Predicate<R> predicate) {
        for (R repository : this) {
            if (predicate.test(repository)) {
                return repository;
            }
        }

        return mainRepository;
    }
}
