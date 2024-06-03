package ru.costa.backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.costa.backend.jwt.payload.request.AccessTokenRequest;
import ru.costa.backend.jwt.JwtAuthenticationProvider;
import ru.costa.backend.jwt.payload.response.TokenResponse;
import ru.costa.backend.jwt.entities.RefreshToken;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Value("${errors.wrong_username_or_password}")
    private String WRONG_USERNAME_OR_PASSWORD;
    private final UserService userService;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    public ResponseEntity<?> generateAccessToken(@NonNull AccessTokenRequest tokenRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(tokenRequest.getUsername(), tokenRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Set<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        String accessToken = jwtAuthenticationProvider.generateAccessToken(userDetails);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(tokenRequest.getUsername());

        return ResponseEntity
                .ok(new TokenResponse(accessToken, refreshToken.getToken(), userDetails.getUsername(), roles));
    }
}
