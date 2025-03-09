package ru.arkanoid.gamebackend;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.arkanoid.gamebackend.update.FixedUpdater;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
public class GameBackendApplication {
    private final FixedUpdater fixedUpdater;

    public static void main(String[] args) {
        SpringApplication.run(GameBackendApplication.class, args);
    }

    @PostConstruct
    private void init() {
        fixedUpdater.start();
    }
}
