package ru.costa.backend.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.stream.Collectors;

/**
 * @author Constantin Vashchuk
 */

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    private static final String PREFIX_BEARER = "Bearer ";
    private static final String HEADER_NAME = "Authorization";
    @Value("${errors.token_expired}")
    private static String TOKEN_EXPIRED;
    @Value("${errors.invalid_signature}")
    private static String INVALID_SIGNATURE;
    private String username;
    private String jwt;

    private final JwtAuthenticationProvider authenticationProvider;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(HEADER_NAME);
        if (authHeader != null && authHeader.startsWith(PREFIX_BEARER)) {
            jwt = authHeader.substring(PREFIX_BEARER.length());
            try {
                username = authenticationProvider.getUsernameFromClaims(jwt);
            } catch (ExpiredJwtException e) {
                log.error(TOKEN_EXPIRED);
            } catch (SignatureException e) {
                log.error(INVALID_SIGNATURE);
            }
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    authenticationProvider.getRolesFromClaims(jwt)
                            .stream()
                            .map(SimpleGrantedAuthority::new).collect(Collectors.toList())
            );
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        filterChain.doFilter(request, response);
    }

}

