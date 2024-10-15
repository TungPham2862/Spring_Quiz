package com.example.springquiz.config;

import com.example.springquiz.Enum.RoleName;
import com.example.springquiz.model.domain.Role;
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

    private final String [] PUBLIC_ENDPOINTS = {
            "/login",
            "/signup",
            "/introspect"};

    private final String[] WHITELIST = {
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/swagger-resources/*",
            "/v3/api-docs/**",
            "/api-docs",
            "/api-docs/swagger-config",
            "/swagger-config"
    };
    private final String[] AUTHOR_ADMIN = {
        "/accounts",
        "/deactivate/",
        "/accounts/role/"
    };
    private final String[] AUTHOR_USER = {
            "/quizzes/**",
            "/questions/**",
            "/answers/**",
    };
    private final String[] AUTHEN_ALLOW = {
            "/profile/**"
    };
    private String secretKey ="h61e6r+A3LQH4PdQWVnQxwMQ2W8CGIv+kaPl2kyB0dr9XcpMOVYCCBMGS6eD0Oh";
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(PUBLIC_ENDPOINTS).permitAll()
                                .requestMatchers(WHITELIST).permitAll()
                                .requestMatchers(AUTHOR_ADMIN).hasAuthority("SCOPE_" + RoleName.Admin.name())
                                .requestMatchers(AUTHOR_USER).hasAuthority("SCOPE_" + RoleName.User.name())
                                .requestMatchers(AUTHEN_ALLOW).hasAnyAuthority("SCOPE_" + RoleName.Admin.name(),"SCOPE_" +  RoleName.User.name())
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