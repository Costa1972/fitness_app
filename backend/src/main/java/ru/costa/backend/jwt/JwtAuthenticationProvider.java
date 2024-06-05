package ru.costa.backend.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
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

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationProvider.class);

    @Value("${jwt.private_secret}")
    private String secret;
    @Value("${jwt.access_token_lifetime}")
    private int accessTokenLifetime;


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
     * @return
     */
    public String generateAccessToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        return Jwts.builder()
                .subject((userPrincipal.getUsername()))
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + accessTokenLifetime))
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

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
    public String getUsernameFromJwtToken(String authToken) {
        return Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(authToken)
                .getPayload()
                .getSubject();
    }

    public Claims getAllClaimsFromToken(String token) {
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
