package com.sp.auth_service.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNullApi;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("authority");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        Long userId = userId = jwtUtil.extractUserIdFromToken(token);

        Set<Long> allowedAuthorities = new HashSet<>();

        try {
            allowedAuthorities = jwtUtil.extractAllowedAuthorities(token);
        } catch (Exception e) {
            filterChain.doFilter(request, response);
        }

        var authorities = allowedAuthorities
                .stream()
                .map(auth -> new SimpleGrantedAuthority("ROLE_" + auth ))
                .collect(Collectors.toSet());

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(userId , null , authorities);
        authToken.setDetails(new WebAuthenticationDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }
}
