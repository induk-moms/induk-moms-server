package com.project.surferthon_inha.login.oauth2.handler;

import com.project.surferthon_inha.login.jwt.dto.response.JWTsResponse;
import com.project.surferthon_inha.login.jwt.entity.UserInformInToken;
import com.project.surferthon_inha.login.jwt.util.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class Oauth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserInformInToken userInform = (UserInformInToken) authentication.getPrincipal();
        Long id = userInform.getId();
        String sub = userInform.getSub();
        String role = userInform.getRole();

        String accessToken = jwtUtil.generateAccessToken(id, sub, role);
        String refreshToken = jwtUtil.generateRefreshToken(id, sub, role);
        String tokens = objectMapper.writeValueAsString(new JWTsResponse(accessToken, refreshToken));

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(tokens);
    }
}
