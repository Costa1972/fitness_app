package ru.costa.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.costa.backend.jwt.payload.request.AccessTokenRequest;
import ru.costa.backend.jwt.payload.request.SignupRequest;
import ru.costa.backend.services.AuthenticationService;

/**
 * @author Constantin Vashchuk
 */

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody AccessTokenRequest request) {
        return ResponseEntity.ok(authenticationService.authenticateUser(request));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignupRequest request) {
        return ResponseEntity.ok(authenticationService.registerUser(request));
    }
}
