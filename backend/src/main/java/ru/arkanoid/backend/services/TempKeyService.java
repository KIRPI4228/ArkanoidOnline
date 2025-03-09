package ru.arkanoid.backend.services;

import java.util.UUID;

public interface TempKeyService {
    String generate(UUID uuid);
    boolean accept(String key, UUID uuid);
}
