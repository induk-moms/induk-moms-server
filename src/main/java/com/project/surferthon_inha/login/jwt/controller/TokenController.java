package com.project.surferthon_inha.login.jwt.controller;

import com.project.surferthon_inha.login.jwt.dto.request.ReIssueTokenRequestDTO;
import com.project.surferthon_inha.login.jwt.dto.response.JWTsResponse;
import com.project.surferthon_inha.login.jwt.service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/reIssue")
    public ResponseEntity<JWTsResponse> reIssue(@RequestBody @Valid ReIssueTokenRequestDTO requestDTO) {
        return new ResponseEntity<>(tokenService.reIssue(requestDTO.getRefreshToken()), HttpStatus.OK);
    }
}
