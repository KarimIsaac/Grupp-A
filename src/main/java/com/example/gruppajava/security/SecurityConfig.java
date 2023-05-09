package com.example.gruppajava.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors()
                .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/api/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/index.html").anonymous()
                .requestMatchers(HttpMethod.GET, "/loggedin.html").anonymous()
                .requestMatchers(HttpMethod.GET, "/mypage.html").anonymous()
                .requestMatchers(HttpMethod.GET, "/js/js.js").anonymous()
                .requestMatchers(HttpMethod.GET, "/js/mypage.js").anonymous()
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder
                .withJwkSetUri("http://host.docker.internal:8000/realms/sharkPark/protocol/openid-connect/certs")
                .jwsAlgorithm(SignatureAlgorithm.ES256)
                .build();
    }
}