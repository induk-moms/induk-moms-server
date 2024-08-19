package com.project.surferthon_inha.common.config;

import com.project.surferthon_inha.login.jwt.fiiter.JWTAuthorizationFilter;
import com.project.surferthon_inha.login.jwt.fiiter.JWTAuthenticationFilter;
import com.project.surferthon_inha.login.jwt.util.JWTUtil;
import com.project.surferthon_inha.login.oauth2.handler.Oauth2SuccessHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final ObjectMapper objectMapper;

    private final JWTUtil jwtUtil;

    private final Oauth2SuccessHandler oauth2SuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        return http
                .cors(Customizer.withDefaults()) //cors config bean 설정을 해놓았기 때문에 withDefaults()로 설정 가능
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable) // username, password 헤더 로그인 방식 해제 (BasicAuthenticationFilter 비활성화)

                .formLogin(Customizer.withDefaults()) //TODO 테스트용 form login
                //.formLogin(AbstractHttpConfigurer::disable) //form 로그인 해제 (UsernamePasswordAuthenticationFilter 비활성화)
                .oauth2Login(oauth2login -> oauth2login
                        .successHandler(oauth2SuccessHandler) // oauth2 로그인 성공 핸들러 설정
                        .defaultSuccessUrl("/")
                ).sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .logout(AbstractHttpConfigurer::disable) // 세션 기반 로그아웃 기능, JWT는 세션을 사용하지 않기 때문에 비활성화

                .addFilterAt(new JWTAuthenticationFilter(jwtUtil, objectMapper, authenticationManager), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(new JWTAuthorizationFilter(jwtUtil), JWTAuthenticationFilter.class)

                .build();
    }
}