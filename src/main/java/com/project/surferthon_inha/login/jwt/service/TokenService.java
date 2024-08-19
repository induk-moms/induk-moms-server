package com.project.surferthon_inha.login.jwt.service;

import com.project.surferthon_inha.login.jwt.dto.response.JWTsResponse;
import com.project.surferthon_inha.login.jwt.entity.BlackListRefreshToken;
import com.project.surferthon_inha.login.jwt.entity.UserInformInToken;
import com.project.surferthon_inha.login.jwt.exception.TokenNotValidException;
import com.project.surferthon_inha.login.jwt.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JWTUtil jwtUtil;

    private final BlackListRefreshTokenService blackListRefreshTokenService;

    public JWTsResponse reIssue(String refreshToken) {
        if (!jwtUtil.validateRefreshToken(refreshToken)) throw new TokenNotValidException();

        UserInformInToken userInform = jwtUtil.getUserInformInRefreshToken(refreshToken);

        String newAccessToken = jwtUtil.generateAccessToken(userInform.getId(), userInform.getSub(), userInform.getRole());
        String newRefreshToken = jwtUtil.generateRefreshToken(userInform.getId(), userInform.getSub(), userInform.getRole());

        blackListRefreshTokenService.save(new BlackListRefreshToken(refreshToken, jwtUtil.getRefreshTokenRemainTime(refreshToken)));

        return new JWTsResponse(newAccessToken, newRefreshToken);
    }

    public void blackListToken(String refreshToken) {
        blackListRefreshTokenService.save(new BlackListRefreshToken(refreshToken, jwtUtil.getRefreshTokenRemainTime(refreshToken)));
    }
}
