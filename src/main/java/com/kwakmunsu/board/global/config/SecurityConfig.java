package com.kwakmunsu.board.global.config;


import static com.kwakmunsu.board.member.entity.Role.ADMIN;
import static com.kwakmunsu.board.member.entity.Role.MEMBER;

import com.kwakmunsu.board.global.jwt.filter.JwtFilter;
import com.kwakmunsu.board.global.jwt.handler.JwtAccessDeniedHandler;
import com.kwakmunsu.board.global.jwt.handler.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtFilter jwtFilter;
    private final String[] adminUrl = {"/admin/**"};
    private final String[] permitAllUrl = {
            "/", "/error", "/h2-console/**", "/auth/**", "/swagger/**", "/swagger-ui/**",
            "/v3/api-docs/**"
    };
    private final String[] hasRoleUrl = {
            "/posts/**", "/members/**", "/comments/**", "/favorites/**"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(permitAllUrl).permitAll()
                        .requestMatchers(adminUrl).hasRole(ADMIN.name())
                        .requestMatchers(hasRoleUrl).hasAnyRole(ADMIN.name(), MEMBER.name())
                        .anyRequest().authenticated());

        http
                .exceptionHandling(handle -> handle
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                        .accessDeniedHandler(jwtAccessDeniedHandler));

        return http.build();
    }

}