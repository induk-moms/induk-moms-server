package com.project.surferthon_inha.common.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommonController {

    @GetMapping("/")
    public ResponseEntity<String> index() {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Test Success", HttpStatus.OK);
    }
}
