package ru.costa.backend.jwt.payload.request;

import lombok.Data;

/**
 * @author Constantin Vashchuk
 */

@Data
public class AccessTokenRequest {
    private String username;
    private String password;
}
