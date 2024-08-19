package com.project.surferthon_inha.login.jwt.repository;

import com.project.surferthon_inha.login.jwt.entity.BlackListRefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<BlackListRefreshToken, String> {

    //READ
    Optional<BlackListRefreshToken> findById(String refreshToken);
}
