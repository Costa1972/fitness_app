package ru.costa.backend.jwt.payload.response;

import lombok.Data;
import ru.costa.backend.jwt.entities.RefreshToken;

import java.util.Set;

/**
 * @author Constantin Vashchuk
 */

@Data
//@AllArgsConstructor
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
    private String type = "Bearer";
    private String username;
    private Set<String> roles;

    public TokenResponse(String accessToken, String refreshToken, String username, Set<String> roles) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.username = username;
        this.roles = roles;
    }
}
