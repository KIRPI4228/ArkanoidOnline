package ru.arkanoid.gamebackend.helpers;

import java.sql.Timestamp;
import java.time.Instant;

public final class TimeHelper {
    public static long getCurrentMillis() {
        return getCurrentTimestamp().getTime();
    }
    public static Timestamp getCurrentTimestamp() {
        return Timestamp.from(Instant.now());
    }
}
