package ru.arkanoid.gamebackend.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionUpdateModel {
    private GameObjectUpdateModel ball;
    private GameObjectUpdateModel platform;
    private DropGameObjectUpdateModel[] drops;
}
