package com.dsi_group.modu_shop_bs_microservice_user.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final KeycloakAuthenticationConverter keycloakAuthenticationConverter;

    public SecurityConfig(KeycloakAuthenticationConverter keycloakAuthenticationConverter) {
        this.keycloakAuthenticationConverter = keycloakAuthenticationConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        httpSecurity.oauth2ResourceServer(oauth2 ->
                oauth2.jwt(configurer ->
                        configurer.jwtAuthenticationConverter(keycloakAuthenticationConverter)
                )
        );
        return httpSecurity.build();
    }
}
