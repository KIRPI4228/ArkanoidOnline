package ru.arkanoid.backend.models.finance.history;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Date;
import java.util.UUID;

@Document("users")
@Builder
@Data
public class TransactionHistoryNodeDatabaseModel {
    @Id
    private UUID uuid;

    @Field(name = "date", targetType = FieldType.DATE_TIME)
    private Date date;
    @Field("description")
    private String description;
    @Field("amount")
    private int amount;
    @Field("balance")
    private int balance;
}
