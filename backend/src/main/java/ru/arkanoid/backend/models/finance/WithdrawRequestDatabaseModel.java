package ru.arkanoid.backend.models.finance;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Data
@Builder
@Document("withdraws")
public class WithdrawRequestDatabaseModel {
    @Id
    private UUID uuid;

    @Field("type")
    private String type;
    @Field("status")
    private String status;
    @Field("requisites")
    private String requisites;
    @Field("user_uuid")
    private UUID userUuid;
    @Field("amount")
    private int amount;
}
