package ru.arkanoid.backend.models.user;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.arkanoid.backend.models.finance.WalletDatabaseModel;

import java.util.UUID;

@Document("users")
@Builder
@Data
public class UserDatabaseModel {
    @Id
    private UUID uuid;

    @Field("username")
    private String username;
    @Field("email")
    private String email;
    @Field("password")
    private String password;
    @Field("role")
    private String role;
    @Field("referral")
    private String referral;
    @Field("wallet")
    private WalletDatabaseModel wallet;
}
