package com.project.surferthon_inha.login.jwt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInformInToken {

    private Long id;

    private String sub;

    private String role;
}
