package ru.arkanoid.gamebackend.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HitBoxModel {
    private String id;
    private int score;
}
