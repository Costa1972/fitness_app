package ru.costa.backend.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.costa.backend.errors.TokenRefreshException;
import ru.costa.backend.jwt.entities.RefreshToken;
import ru.costa.backend.jwt.repository.RefreshTokenRepository;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    @Value("${jwt.refresh_token_lifetime}")
    private Long refreshTokenDuration;
    private final UserService userService;
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken createRefreshToken(String username) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(userService.getUserByUsername(username).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDuration));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) throws TokenRefreshException {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    @Transactional
    public int deleteByUserId(Long userId) {
        return refreshTokenRepository.deleteByUser(userService.getUserById(userId).get());
    }
}
