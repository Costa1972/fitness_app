package ru.costa.backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.costa.backend.errors.ApplicationError;
import ru.costa.backend.jwt.AccessTokenRequest;
import ru.costa.backend.jwt.JwtAuthenticationProvider;
import ru.costa.backend.jwt.TokenResponse;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Value("${errors.wrong_username_or_password}")
    private String WRONG_USERNAME_OR_PASSWORD;
    private final UserService userService;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> generateAccessToken(@NonNull AccessTokenRequest tokenRequest) {
        Map<String, String> refreshStorage = new HashMap<>();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(tokenRequest.getUsername(), tokenRequest.getPassword()));
        } catch (BadCredentialsException badCredentialsException) {
            return new ResponseEntity<>(new ApplicationError(HttpStatus.UNAUTHORIZED.value(), WRONG_USERNAME_OR_PASSWORD), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(tokenRequest.getUsername());
        String accessToken = jwtAuthenticationProvider.generateAccessToken(userDetails);
        String refreshToken = jwtAuthenticationProvider.generateRefreshToken(userDetails);
        refreshStorage.put("token", refreshToken);
        return ResponseEntity.ok(new TokenResponse(accessToken, refreshToken));
    }
}
