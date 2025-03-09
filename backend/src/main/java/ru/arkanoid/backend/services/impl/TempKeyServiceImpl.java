package ru.arkanoid.backend.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.arkanoid.backend.repositories.temp.key.TempKeyRepository;
import ru.arkanoid.backend.services.TempKeyService;

import java.security.SecureRandom;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TempKeyServiceImpl implements TempKeyService {
    private static final SecureRandom generator = new SecureRandom();

    private final TempKeyRepository repository;

    @Override
    public String generate(UUID uuid) {
        String key = String.valueOf(generator.nextLong());

        repository.add(key, uuid);
        return key;
    }

    @Override
    public boolean accept(String key, UUID uuid) {
        if (repository.contains(key) && repository.hasKey(uuid, key)) {
            repository.remove(key);
            return true;
        }

        return false;
    }
}
