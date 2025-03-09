package ru.arkanoid.gamebackend.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameOverModel {
    private boolean state;
    // TODO: 21.06.2024 Make statistics
}
