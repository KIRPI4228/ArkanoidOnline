package ru.arkanoid.gamebackend.services;

import ru.arkanoid.gamebackend.models.GameOverModel;
import ru.arkanoid.gamebackend.models.HitBoxModel;
import ru.arkanoid.gamebackend.models.SessionUpdateModel;
import ru.arkanoid.gamebackend.session.Session;
import ru.arkanoid.gamebackend.session.levels.Level;
import ru.arkanoid.gamebackend.update.Updater;
import ru.arkanoid.gamebackend.update.scheduler.Scheduler;

import java.io.IOException;
import java.util.function.Consumer;

public interface LevelService {
    Level loadJson(Session session,
                   Updater updater,
                   Scheduler scheduler,
                   Consumer<HitBoxModel> hitBoxCallback,
                   Consumer<GameOverModel> gameOverCallback,
                   Consumer<SessionUpdateModel> sessionUpdateCallback,
                   String path) throws IOException;

    Level generateTemplate(Session session,
                           Updater updater,
                           Scheduler scheduler,
                           Consumer<HitBoxModel> hitBoxCallback,
                           Consumer<GameOverModel> gameOverCallback,
                           Consumer<SessionUpdateModel> sessionUpdateCallback);
}
