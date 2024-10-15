package com.example.springquiz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;
import java.net.http.HttpRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final String [] PUBLIC_POST_ENDPOINTS = {"/login",
                        "/signup"};
    private static final String[] AUTH_WHITELIST = {
            "/authenticate",
            "/swagger-ui/**",
            "/swagger-resources/*",
            "/v3/api-docs/**",
            "/api-docs",
            "/api/v1/app/user/auth/"
    };
    private String secretKey ="h61e6r+A3LQH4PdQWVnQxwMQ2W8CGIv+kaPl2kyB0dr9XcpMOVYCCBMGS6eD0Oh";
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(HttpMethod.POST, PUBLIC_POST_ENDPOINTS).permitAll()
                                .requestMatchers(HttpMethod.GET, "/accounts").permitAll()
                                .requestMatchers("/authenticate",
                                        "/swagger-ui.html",
                                        "/swagger-ui/**",
                                        "/swagger-resources/*",
                                        "/v3/api-docs/**",
                                        "/api-docs").permitAll()
                                .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable);


        httpSecurity.oauth2ResourceServer(oauth2ResourceServer ->
                oauth2ResourceServer.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())));
        return httpSecurity.build();
    }

    public JwtDecoder jwtDecoder() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HS256");
        return NimbusJwtDecoder.withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
    }
}