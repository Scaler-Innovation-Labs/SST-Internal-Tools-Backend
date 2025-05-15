package com.sstinternaltools.sstinternal_tools.security.config;

import com.sstinternaltools.sstinternal_tools.security.service.implementation.CustomOAuth2SuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final JwtFilter jwtFilter;
    private final CustomOAuth2SuccessHandler successHandler;

    public SecurityConfiguration(JwtFilter jwtFilter,
                                 CustomOAuth2SuccessHandler successHandler) {
        this.jwtFilter = jwtFilter;
        this.successHandler = successHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/", "/index.html", "/favicon.ico",
                                "/static/**", "/auth/**", "/oauth2/**"
                        ).permitAll()
                        .anyRequest().authenticated()          // everything under /api/**
                )

                .oauth2Login(oauth -> oauth.successHandler(successHandler))

                /* 🔥 Run JwtFilter AFTER the OAuth2 workflow is done */
                .addFilterAfter(jwtFilter, OAuth2LoginAuthenticationFilter.class)

                .sessionManagement(s ->
                        s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }
}
