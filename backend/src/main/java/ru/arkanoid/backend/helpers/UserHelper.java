package ru.arkanoid.backend.helpers;

import java.util.UUID;

public final class UserHelper {
    public static UUID getUuidFromEmail(String email) {
        return UUID.nameUUIDFromBytes(email.getBytes());
    }
}
