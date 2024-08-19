package com.project.surferthon_inha.login.jwt.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@Setter
@RedisHash(value = "refreshToken", timeToLive = 86400)
public class BlackListRefreshToken {

    @Id
    private String refreshToken;

    @TimeToLive
    private Long expiration;

    public BlackListRefreshToken(String refreshToken, Long expiration) {
        this.refreshToken = refreshToken;
        this.expiration = expiration;
    }
}
