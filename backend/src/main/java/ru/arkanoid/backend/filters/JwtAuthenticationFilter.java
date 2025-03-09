package ru.arkanoid.backend.filters;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.arkanoid.backend.serializers.TokenSerializer;
import ru.arkanoid.backend.serializers.user.UserJwtTokenSerializer;
import ru.arkanoid.backend.services.JwtService;
import ru.arkanoid.backend.user.User;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String HEADER_NAME = "Authorization";

    private final JwtService jwtService;
    private final TokenSerializer tokenSerializer;
    private final UserJwtTokenSerializer userJwtTokenSerializer;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        var authHeader = request.getHeader(HEADER_NAME);

        var token = tokenSerializer.serialize(authHeader);
        if (token == null) {

            filterChain.doFilter(request, response);
            return;
        }

        try {
            var userModel = jwtService.getUserModel(token);

            if (userModel != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                User user = userJwtTokenSerializer.serialize(userModel);

                if (user != null && jwtService.isTokenValid(token, user)) {
                    var authenticationToken = new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            user.getAuthorities()
                    );

                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    var context = SecurityContextHolder.createEmptyContext();

                    context.setAuthentication(authenticationToken);

                    SecurityContextHolder.setContext(context);
                    response.setStatus(HttpServletResponse.SC_ACCEPTED);
                }
            }
        } catch (JwtException exception) {
            response.setStatus(460);
        }

        filterChain.doFilter(request, response);
    }
}
