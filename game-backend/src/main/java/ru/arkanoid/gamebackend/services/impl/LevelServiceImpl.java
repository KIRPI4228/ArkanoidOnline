package ru.arkanoid.gamebackend.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.arkanoid.gamebackend.engine.Vector;
import ru.arkanoid.gamebackend.models.GameOverModel;
import ru.arkanoid.gamebackend.models.HitBoxModel;
import ru.arkanoid.gamebackend.models.SessionUpdateModel;
import ru.arkanoid.gamebackend.models.json.JsonLevelModel;
import ru.arkanoid.gamebackend.services.LevelService;
import ru.arkanoid.gamebackend.session.Session;
import ru.arkanoid.gamebackend.session.levels.Level;
import ru.arkanoid.gamebackend.session.levels.LevelMapper;
import ru.arkanoid.gamebackend.update.Updater;
import ru.arkanoid.gamebackend.update.scheduler.Scheduler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Service
public class LevelServiceImpl implements LevelService {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public Level loadJson(Session session,
                          Updater updater,
                          Scheduler scheduler,
                          Consumer<HitBoxModel> hitBoxCallback,
                          Consumer<GameOverModel> gameOverCallback,
                          Consumer<SessionUpdateModel> sessionUpdateCallback,
                          String path) throws IOException {

        var levelModel = OBJECT_MAPPER.readValue(new File(path), JsonLevelModel.class);


        var levelMapper = LevelMapper.builder()
                .ballSpeed(levelModel.getBallSpeed()) //0,0476
                .ballRadius(levelModel.getBallRadius()) //0,2
                .platformSpeed(levelModel.getPlatformSpeed()) //0,098
                .platformSize(levelModel.getPlatformSize()) //2,5
                .gap(levelModel.getGap()) // 1,5 1,5 0
                .lines(levelModel.getLines())
                .build();

        return levelMapper.generateLevel(session, updater, scheduler, hitBoxCallback, gameOverCallback, sessionUpdateCallback);
    }

    @Override
    public Level generateTemplate(Session session,
                                  Updater updater,
                                  Scheduler scheduler,
                                  Consumer<HitBoxModel> hitBoxCallback,
                                  Consumer<GameOverModel> gameOverCallback,
                                  Consumer<SessionUpdateModel> sessionUpdateCallback) {

        // TODO: 05.06.2024 Make demo level generation

        var drop = "DROP<PLATFORM_EXPAND>";
        var def = "DEFAULT";

        var type = drop;

        List<List<String>> lines = new ArrayList<>();

        List<String> line1 = new ArrayList<>();
        line1.add(type + ";30;BLUE");
        line1.add(type + ";30;BLUE");
        line1.add(type + ";30;BLUE");
        line1.add(type + ";30;BLUE");
        line1.add(type + ";30;BLUE");
        line1.add(type + ";30;BLUE");
        line1.add(type + ";30;BLUE");
        line1.add(type + ";30;BLUE");

        List<String> line2 =  new ArrayList<>();
        line2.add(type + ";20;YELLOW");
        line2.add(type + ";20;YELLOW");
        line2.add(type + ";20;YELLOW");
        line2.add(type + ";20;YELLOW");
        line2.add(type + ";20;YELLOW");
        line2.add(type + ";20;YELLOW");
        line2.add(type + ";20;YELLOW");
        line2.add(type + ";20;YELLOW");

        List<String> line3 =  new ArrayList<>();
        line3.add(type + ";15;RED");
        line3.add(type + ";15;RED");
        line3.add(type + ";15;RED");
        line3.add(type + ";15;RED");
        line3.add(type + ";15;RED");
        line3.add(type + ";15;RED");
        line3.add(type + ";15;RED");
        line3.add(type + ";15;RED");

        List<String> line4 =  new ArrayList<>();
        line4.add(type + ";10;GREEN");
        line4.add(type + ";10;GREEN");
        line4.add(type + ";10;GREEN");
        line4.add(type + ";10;GREEN");
        line4.add(type + ";10;GREEN");
        line4.add(type + ";10;GREEN");
        line4.add(type + ";10;GREEN");
        line4.add(type + ";10;GREEN");

        lines.add(line1);
        lines.add(line3);
        lines.add(line4);
        lines.add(line2);

        var mapper = LevelMapper.builder()
                .ballSpeed(0.68f * 0.7f * 0.1f)
                .ballRadius(0.2f)
                .platformSpeed(1.4f * 0.7f * 0.1f)
                .platformSize(2.5f)
                .gap(new Vector(1.5f, 1.5f, 0))
                .lines(lines)
                .build();

        return mapper.generateLevel(session, updater, scheduler, hitBoxCallback, gameOverCallback, sessionUpdateCallback);
    }
}
