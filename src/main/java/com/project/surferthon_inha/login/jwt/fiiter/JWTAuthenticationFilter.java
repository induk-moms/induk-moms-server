package com.project.surferthon_inha.login.jwt.fiiter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.surferthon_inha.login.jwt.dto.response.JWTsResponse;
import com.project.surferthon_inha.login.jwt.util.JWTUtil;
import com.project.surferthon_inha.login.user.dto.request.UserLoginRequestDTO;
import com.project.surferthon_inha.login.user.entity.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JWTUtil jwtUtil;

    private final ObjectMapper objectMapper;

    private final AuthenticationManager authenticationManager;

    @Value("${frontEnd.loginRedirectUrl}")
    private String frontEndLoginRedirectUrl;

    @Builder
    public JWTAuthenticationFilter(JWTUtil jwtUtil, ObjectMapper objectMapper, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.objectMapper = objectMapper;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            log.debug("attempt authentication");

            UserLoginRequestDTO userLoginRequestDTO;
            if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
                userLoginRequestDTO = objectMapper.readValue(request.getInputStream(), UserLoginRequestDTO.class);
            }
            else { // json context type이 아닌 form content type을 지원하기 위해
                userLoginRequestDTO = new UserLoginRequestDTO();
                userLoginRequestDTO.setSub(request.getParameter("username"));
                userLoginRequestDTO.setPassword(request.getParameter("password"));
            }

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userLoginRequestDTO.getSub(), userLoginRequestDTO.getPassword());

            return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AuthenticationServiceException("failed to parse userLoginRequestDTO from request");
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthenticationServiceException("attempt authentication failed by unexpected reason");
        }
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.debug("successful authentication");
        CustomUserDetails userDetails = (CustomUserDetails) authResult.getPrincipal();
        Long id = userDetails.getUser().getId();
        String sub = userDetails.getUser().getSub();
        String role = userDetails.getAuthorities().iterator().next().getAuthority();

        String accessToken = jwtUtil.generateAccessToken(id, sub, role);
        String refreshToken = jwtUtil.generateRefreshToken(id, sub, role);
        String tokens = objectMapper.writeValueAsString(new JWTsResponse(accessToken, refreshToken));

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(tokens);

        //TODO 프론트 적용 시 변경
        //String origin = request.getHeader("origin");
        //response.sendRedirect(origin);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.debug("unsuccessful authentication");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
