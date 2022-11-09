package org.project.stock.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.SneakyThrows;
import org.project.stock.security.context.UserContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.AccessException;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtAction {

    private String secret;
    private Long timeLifeToken;

    public JwtAction(@Value("${jwt.secret.access}") String secret,
                     @Value("${jwt.timeLife}") Long timeLifeToken) {
        this.secret = secret;
        this.timeLifeToken = timeLifeToken;
    }

    public String generateToken(@NotNull UserContext userContext) {
        LocalDateTime timeNow = LocalDateTime.now();
        Date startLifeCycleToken = Date.from(timeNow.atZone(ZoneId.systemDefault()).toInstant());
        Date endLifeCycleToken = Date.from(timeNow.plusMinutes(timeLifeToken)
                .atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setNotBefore(startLifeCycleToken)
                .setIssuedAt(endLifeCycleToken)
                .setSubject(userContext.getLogin())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    @SneakyThrows
    public boolean validateToken(@NotNull String token) {
        try {
            Jwts.parser().setSigningKey(secret).parse(token);
            return true;
        } catch (Exception e) {
            throw new AccessException("Неправильные данные пользователя!");
        }
    }

    public Claims getClaims(@NotNull String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

}
