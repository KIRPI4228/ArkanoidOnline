package ru.arkanoid.gamebackend.models.json;

import lombok.*;
import ru.arkanoid.gamebackend.engine.Vector;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JsonLevelModel {
    private List<List<String>> lines;
    private float ballSpeed;
    private float ballRadius;
    private float platformSize;
    private float platformSpeed;
    private Vector gap;
}
