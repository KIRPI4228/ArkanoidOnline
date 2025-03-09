package ru.arkanoid.backend.services.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.arkanoid.backend.exceptions.ServiceException;
import ru.arkanoid.backend.helpers.TimeHelper;
import ru.arkanoid.backend.models.user.UserJwtTokenModel;
import ru.arkanoid.backend.models.user.UserResetPasswordVerificationJwtTokenModel;
import ru.arkanoid.backend.models.user.UserVerificationJwtTokenModel;
import ru.arkanoid.backend.services.JwtService;
import ru.arkanoid.backend.user.Role;
import ru.arkanoid.backend.user.User;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    private static final long TOKEN_DURATION = TimeUnit.DAYS.toMillis(2);
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    @Value("${jwt.key.secret}")
    private String key;

    @Override
    public UserJwtTokenModel getUserModel(String token) {
        if (!isTokenTrusted(token)) {
            throw new ServiceException("error.session.not_valid");
        }

        if (getClaim(token, "isVerification", Boolean.class)) {
            return null;
        }

        return UserJwtTokenModel.builder()
                .uuid(getClaim(token, claims -> UUID.fromString(claims.getSubject())))
                .email(getClaim(token, "email", String.class))
                .build();
    }

    @Override
    public UserVerificationJwtTokenModel getUserVerificationModel(String token) {
        if (!isTokenTrusted(token)) {
            throw new ServiceException("error.session.not_valid");
        }

        if (!getClaim(token, "isVerification", Boolean.class)) {
            return null;
        }

        return UserVerificationJwtTokenModel.builder()
                .uuid(getClaim(token, claims -> UUID.fromString(claims.getSubject())))
                .email(getClaim(token, "email", String.class))
                .password(getClaim(token, "password", String.class))
                .username(getClaim(token, "username", String.class))
                .referral(getClaim(token, "referral", String.class))
                .role(Role.valueOf(getClaim(token, "role", String.class)))
                .build();
    }

    @Override
    public UserResetPasswordVerificationJwtTokenModel getUserResetPasswordVerificationModel(String token) {
        return UserResetPasswordVerificationJwtTokenModel.builder()
                .uuid(getClaim(token, claims -> UUID.fromString(claims.getSubject())))
                .email(getClaim(token, "email", String.class))
                .referral(getClaim(token, "referral", String.class))
                .password(getClaim(token, "password", String.class))
                .build();
    }

    @Override
    public String generateToken(UserJwtTokenModel model) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", model.getEmail());
        claims.put("isVerification", false);

        return generateToken(claims, model.getUuid());
    }

    @Override
    public String generateToken(UserVerificationJwtTokenModel model) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", model.getUsername());
        claims.put("email", model.getEmail());
        claims.put("password", model.getPassword());
        claims.put("role", model.getRole().name());
        claims.put("referral", model.getReferral());
        claims.put("isVerification", true);

        return generateToken(claims, model.getUuid());
    }

    @Override
    public String generateToken(UserResetPasswordVerificationJwtTokenModel model) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", model.getEmail());
        claims.put("referral", model.getReferral());
        claims.put("password", model.getPassword());
        claims.put("isVerification", true);

        return generateToken(claims, model.getUuid());
    }

    @Override
    public boolean isTokenValid(String token, User user) {
        try {
            var model = getUserModel(token);
            if (model == null) {
                return false;
            }

            return isTokenValid(token, user, model);
        } catch (JwtException exception) {
            return false;
        }
    }

    @Override
    public boolean isTokenTrusted(String token) {
        try {
            getAllClaims(token);
            return true;
        } catch (JwtException exception) {
            return false;
        }

    }

    private boolean isTokenValid(String token, User user, UserJwtTokenModel model) {
        return user.getUuid().equals(model.getUuid()) && !isTokenExpired(token);
    }

    private String generateToken(Map<String, Object> claims, UUID uuid) {
        return Jwts.builder()
                .setClaims(claims)
                .subject(uuid.toString())
                .issuedAt(new Date(TimeHelper.getCurrentMillis()))
                .expiration(new Date(TimeHelper.getCurrentMillis() + TOKEN_DURATION))
                .signWith(getKey(), SIGNATURE_ALGORITHM)
                .compact();
    }

    private <T> T getClaim(String token, String claimName, Class<T> requiredType) {
        return getClaim(token, claims -> claims.get(claimName, requiredType));
    }

    private <T> T getClaim(String token, Function<Claims, T> resolver) {
        return resolver.apply(getAllClaims(token));
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser().setSigningKey(getKey()).build().parseSignedClaims(token).getPayload();
    }

    private Key getKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
    }
}
