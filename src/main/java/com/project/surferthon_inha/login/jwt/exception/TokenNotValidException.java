package com.project.surferthon_inha.login.jwt.exception;

public class TokenNotValidException extends RuntimeException {
    public TokenNotValidException() {
        super("Token is not valid");
    }
}
