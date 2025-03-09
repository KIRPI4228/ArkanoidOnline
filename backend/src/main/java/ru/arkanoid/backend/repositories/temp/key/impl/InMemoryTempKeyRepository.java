package ru.arkanoid.backend.repositories.temp.key.impl;

import org.springframework.stereotype.Repository;
import ru.arkanoid.backend.repositories.temp.key.TempKeyRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class InMemoryTempKeyRepository implements TempKeyRepository {
    private final Map<String, UUID> KEYS = new HashMap<>();

    @Override
    public void add(String key, UUID uuid) {
        KEYS.put(key, uuid);
    }

    @Override
    public void remove(String key) {
        KEYS.remove(key);
    }

    @Override
    public boolean contains(String key) {
        return KEYS.containsKey(key);
    }

    @Override
    public boolean hasKey(UUID uuid, String key) {
        return uuid.equals(get(key));
    }

    @Override
    public UUID get(String key) {
        return KEYS.get(key);
    }

    @Override
    public Set<String> getAllKeys(UUID uuid) {
        return KEYS.keySet()
                .stream()
                .filter(key -> uuid.equals(get(key)))
                .collect(Collectors.toSet());
    }
}
