package ru.costa.backend.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Constantin Vashchuk
 */

@Data
@AllArgsConstructor
public class TokenResponse {
    private final String title = "Bearer ";
    private String accessToken;
    private String refreshToken;
}
