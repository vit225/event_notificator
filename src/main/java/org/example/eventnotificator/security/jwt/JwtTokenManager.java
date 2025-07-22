package org.example.eventnotificator.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtTokenManager {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final SecretKey secretKey;


    public JwtTokenManager(
            @Value("${jwt.secret-key}") String keyString
    ) {
        this.secretKey = Keys.hmacShaKeyFor(keyString.getBytes());
    }

    public String getLoginFromToken(String token) {
        log.info("Get login from token = {} ", token);

        return Jwts
                .parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getPayload()
                .getSubject();
    }

    public String getRoleFromToken(String token) {
        log.info("Get role from token = {} ", token);
        return Jwts
                .parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getPayload()
                .get("role", String.class);
    }


    public Integer getIdFromToken(String token) {
        log.info("Get id from token = {} ", token);
        return Jwts
                .parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getPayload()
                .get("userId", Integer.class);
    }
}
