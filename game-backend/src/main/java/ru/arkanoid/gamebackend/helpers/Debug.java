package ru.arkanoid.gamebackend.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Debug {
    private static final Logger LOGGER = LoggerFactory.getLogger("Debug");


    public static void log(String text) {
        LOGGER.info(text);
    }
}
