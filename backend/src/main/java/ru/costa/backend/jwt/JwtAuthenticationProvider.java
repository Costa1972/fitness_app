package ru.costa.backend.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Constantin Vashchuk
 */

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider {

    @Value("${jwt.private_secret}")
    private String secret;
    @Value("${jwt.access_token_lifetime}")
    private Duration accessTokenLifetime;


    /**
     * Формирование claims из пользователя
     * @param userDetails
     * @return
     */
    private Map<String, Object> createClaims(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        List<String> roles = userDetails
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        String subject = userDetails.getUsername();
        claims.put("subject", subject);
        claims.put("roles", roles);
        return  claims;
    }

    /**
     * генерация access token
     * @param userDetails
     * @return
     */
    public String generateAccessToken(UserDetails userDetails) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant accessExpirationInstant = now.plusMinutes(30).atZone(ZoneId.systemDefault()).toInstant();
        final Date accessExpiration = Date.from(accessExpirationInstant);
        return Jwts.builder()
                .claims(createClaims(userDetails))
                .expiration(accessExpiration)
                .signWith(getSigningKey())
                .compact();
    }

    public String generateRefreshToken(UserDetails userDetails) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant accessExpirationInstant = now.plusDays(30).atZone(ZoneId.systemDefault()).toInstant();
        final Date accessExpiration = Date.from(accessExpirationInstant);
        return Jwts.builder()
                .claims(createClaims(userDetails))
                .expiration(accessExpiration)
                .signWith(getSigningKey())
                .compact();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getUsernameFromClaims(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public List<String> getRolesFromClaims(String token) {
        return getAllClaimsFromToken(token).get("roles", List.class);
    }

    public Date getExpirationToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }
}
