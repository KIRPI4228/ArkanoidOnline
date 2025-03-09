package ru.arkanoid.gamebackend.helpers;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class CollectionsHelper {
    public static <T> Set<T> createConcurrentSet() {
        return Collections.newSetFromMap(new ConcurrentHashMap<>());
    }
}
