package com.sp.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Component
public class JwtValidationGatewayFilterFactory
        extends AbstractGatewayFilterFactory<Object> {

    private static final Logger log = LoggerFactory.getLogger(JwtValidationGatewayFilterFactory.class);
    private final WebClient webClient;

    public JwtValidationGatewayFilterFactory(
            WebClient.Builder webClientBuilder,
            @Value("${auth.service.url}") String authServiceUrl
    ) {
        log.info("Calling auth-service at: {}", authServiceUrl + "/validate");
        this.webClient = webClientBuilder
                .baseUrl(authServiceUrl)
                .build();
    }

    // Empty Config (required by Gateway)
    public static class Config {}

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {

            String token = exchange.getRequest()
                    .getHeaders()
                    .getFirst(HttpHeaders.AUTHORIZATION);

            // Reject if token missing
            if (token == null || !token.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            // Call auth-service /validate
            return webClient.get()
                    .uri("/validate")
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .flatMap(map -> {

                        Boolean valid = (Boolean) map.get("valid");
                        log.info("valid: {}", valid);
                        if (valid == null || !valid) {
                            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                            return exchange.getResponse().setComplete();
                        }

                        // extract userId and roles
                        Object uidObj = map.get("userId");
                        String userId = uidObj == null ? "" : String.valueOf(uidObj);

                        // roles could be List<Integer> or List<Long>
                        Object rolesObj = map.get("roles");
                        List<String> rolesList = new ArrayList<>();
                        if (rolesObj instanceof Collection) {
                            for (Object o : (Collection<?>) rolesObj) {
                                rolesList.add(String.valueOf(o)); // keep as plain strings
                            }
                        }

                        // Build authorities as ROLE_<value> or your mapping
                        List<SimpleGrantedAuthority> authorities = rolesList.stream()
                                .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                                .toList();

                        // Create Authentication (Reactive)
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(userId, null, authorities);

                        // Add headers to forward to downstream service
                        log.info("Authentication: {}", rolesList);
                        log.info("username: {}", userId);

                        ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                                .header("X-User-Id", userId)
                                .header("X-Roles", String.join(",", rolesList)) // comma-separated
                                .build();

                        log.info("mutatedRequest: {}", String.join(",", rolesList));

                        ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();

                        // Put authentication into reactive security context for Gateway filters / route predicates
                        return chain.filter(mutatedExchange)
                                .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));

                    })
                    .onErrorResume(err -> {
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return exchange.getResponse().setComplete();
                    });
        };
    }
}
