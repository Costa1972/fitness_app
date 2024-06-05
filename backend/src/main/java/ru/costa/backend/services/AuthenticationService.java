package ru.costa.backend.services;

import jakarta.validation.Valid;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.costa.backend.entities.Role;
import ru.costa.backend.entities.User;
import ru.costa.backend.jwt.payload.request.AccessTokenRequest;
import ru.costa.backend.jwt.JwtAuthenticationProvider;
import ru.costa.backend.jwt.payload.request.SignupRequest;
import ru.costa.backend.jwt.payload.response.MessageResponse;
import ru.costa.backend.jwt.payload.response.TokenResponse;
import ru.costa.backend.jwt.entities.RefreshToken;
import ru.costa.backend.repositories.RoleRepository;
import ru.costa.backend.repositories.UserRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Value("${errors.wrong_username_or_password}")
    private String WRONG_USERNAME_OR_PASSWORD;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    public ResponseEntity<?> authenticateUser(@NonNull AccessTokenRequest tokenRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(tokenRequest.getUsername(), tokenRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Set<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        String accessToken = jwtAuthenticationProvider.generateAccessToken(authentication);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(tokenRequest.getUsername());

        return ResponseEntity
                .ok(new TokenResponse(accessToken, refreshToken.getToken(), userDetails.getUsername(), roles));
    }

    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        if (signUpRequest.getPassword() != null && signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword())) {
            User user = new User(
                    signUpRequest.getUsername(),
                    signUpRequest.getPassword(),
                    signUpRequest.getFirstName(),
                    signUpRequest.getLastName(),
                    signUpRequest.getEmail(),
                    signUpRequest.getPhoneNumber(),
                    signUpRequest.getAge(),
                    signUpRequest.getSex());

            Set<String> strRoles = signUpRequest.getRole();
            Set<Role> roles = new HashSet<>();

            if (strRoles == null) {
                Role userRole = roleRepository.findByName("ROLE_USER")
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "admin":
                            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(adminRole);
                            break;
                        default:
                            Role userRole = roleRepository.findByName("ROLE_USER")
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(userRole);
                    }
                });
            }

            user.setRoles(roles);
            userRepository.save(user);
        }

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
