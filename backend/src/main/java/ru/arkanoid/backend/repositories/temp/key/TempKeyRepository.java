package ru.arkanoid.backend.repositories.temp.key;

import java.util.Set;
import java.util.UUID;

public interface TempKeyRepository {
    void add(String key, UUID uuid);
    void remove(String key);

    boolean contains(String key);
    boolean hasKey(UUID uuid, String key);

    UUID get(String key);
    Set<String> getAllKeys(UUID uuid);
}
