package com.project.surferthon_inha.login.jwt.service;

import com.project.surferthon_inha.login.jwt.entity.BlackListRefreshToken;
import com.project.surferthon_inha.login.jwt.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlackListRefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public void save(BlackListRefreshToken blackListRefreshToken) {
        refreshTokenRepository.save(blackListRefreshToken);
    }

    public boolean exists(String refreshToken) {
        return refreshTokenRepository.existsById(refreshToken);
    }
}
