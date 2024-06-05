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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.costa.backend.services.UserService;

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
    private final UserService userService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            if (jwt != null && authenticationProvider.validateJwtToken(jwt)) {
                String username = authenticationProvider.getUsernameFromJwtToken(jwt);
                UserDetails userDetails = userService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }

        filterChain.doFilter(request, response);
    }
    private String parseJwt(HttpServletRequest request) {
        String authHeader = request.getHeader(HEADER_NAME);

        if (StringUtils.hasText(authHeader) && authHeader.startsWith(PREFIX_BEARER)) {
            return authHeader.substring(7);
        }
        return null;
    }

}

