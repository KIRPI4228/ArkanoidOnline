package ru.arkanoid.gamebackend.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper=false)
public class DropGameObjectUpdateModel extends GameObjectUpdateModel {
    private final String id;
    private final String type;
}
