package com.project.surferthon_inha.login.user.controller;

import com.project.surferthon_inha.login.jwt.entity.UserInformInToken;
import com.project.surferthon_inha.login.user.annotation.AuthUser;
import com.project.surferthon_inha.login.user.dto.request.RegisterRequestDTO;
import com.project.surferthon_inha.login.user.dto.response.UserInformResponseDTO;
import com.project.surferthon_inha.login.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Long> register(@RequestBody @Valid RegisterRequestDTO registerRequestDTO) {
        return new ResponseEntity<>(userService.register(registerRequestDTO.toEntity()), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<UserInformResponseDTO> getUserInform(@AuthUser UserInformInToken userInform) {
        return new ResponseEntity<>(userService.getUserInform(userInform.getId()), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> withdraw(@AuthUser UserInformInToken userInform) {
        userService.withdraw(userInform.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody String refreshToken) {
        userService.logout(refreshToken);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
