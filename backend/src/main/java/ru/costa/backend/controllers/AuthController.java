package ru.costa.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.costa.backend.jwt.AccessTokenRequest;
import ru.costa.backend.services.AuthenticationService;

/**
 * @author Constantin Vashchuk
 */

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccessTokenRequest request) {
        return ResponseEntity.ok(authenticationService.generateAccessToken(request));
    }
}
