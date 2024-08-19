package com.project.surferthon_inha.mapping.userClub.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserClub {

    private Long userId;

    private Long clubId;

    private LocalDateTime registrationDate;
}
