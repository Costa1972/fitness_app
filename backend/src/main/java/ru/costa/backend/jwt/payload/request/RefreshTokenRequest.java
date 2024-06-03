package ru.costa.backend.jwt.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RefreshTokenRequest {
    @NotBlank
    private String refreshToken;
}
