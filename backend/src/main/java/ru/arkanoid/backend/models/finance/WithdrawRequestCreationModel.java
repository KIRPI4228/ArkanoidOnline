package ru.arkanoid.backend.models.finance;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WithdrawRequestCreationModel {
    private String type;
    private String requisites;
    private int amount;
}
