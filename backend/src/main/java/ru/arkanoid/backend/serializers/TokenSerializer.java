package ru.arkanoid.backend.serializers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.arkanoid.backend.services.JwtService;

@Component
@RequiredArgsConstructor
public class TokenSerializer implements Serializer<String, String> {
    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtService jwtService;

    @Override
    public String serialize(String authHeader) {
        if (authHeader == null || authHeader.isEmpty() || !authHeader.startsWith(BEARER_PREFIX)) {
            return null;
        }

        var token = authHeader.substring(BEARER_PREFIX.length());
        if (token.isEmpty() || "null".equals(token) || !jwtService.isTokenTrusted(token)) {
            return null;
        }


        return token;
    }
}
