package com.example.project_with_kimminseo.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@Slf4j
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    public String createToken(Map<String, Object> claims, LocalDateTime expired) {
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());
        var _expired = Date.from(expired.atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(_expired)
                .compact();
    }

    public Claims parseToken(String token) {
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public void validation(String token) {
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());
        var parse = Jwts.parserBuilder().setSigningKey(key).build();

        try{
            var result = parse.parseClaimsJws(token);
            result.getBody().entrySet().forEach((value) -> {
                log.info("key {} value : {}", value.getKey(), value.getValue());
            });
        }catch (Exception e){
            throw new RuntimeException("유효하지 않은 토큰 -> validation");
        }
    }
}
