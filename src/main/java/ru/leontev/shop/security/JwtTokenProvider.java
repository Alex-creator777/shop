package ru.leontev.shop.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * JwtTokenProvider отвечает за генерацию и валидацию JWT токенов.
 * Класс использует io.jsonwebtoken для создания токена с данными (имя пользователя, дата истечения)
 * и проверки его корректности. Секретный ключ считывается из application.properties в формате Base64,
 * преобразуется в объект Key, что гарантирует достаточную длину для алгоритма HS512.
 */
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret; // Секретный ключ в формате Base64 для подписи токенов

    @Value("${jwt.expirationMs}")
    private int jwtExpirationMs; // Время жизни токена в миллисекундах

    private Key key; // Ключ, используемый для подписи и проверки JWT

    /**
     * Метод инициализации, преобразует строковый секретный ключ в объект Key.
     */
    @PostConstruct
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Генерирует JWT токен для заданного имени пользователя.
     *
     * @param username имя пользователя
     * @return JWT токен
     */
    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * Извлекает имя пользователя из JWT токена.
     *
     * @param token JWT токен
     * @return имя пользователя
     */
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    /**
     * Валидирует JWT токен.
     *
     * @param authToken JWT токен
     * @return true, если токен валиден; иначе false
     */
    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
            return true;
        } catch (JwtException ex) {
            // Здесь можно добавить логирование ошибки, если необходимо
        }
        return false;
    }
}
