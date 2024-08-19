package com.project.surferthon_inha.mapping.clubReview.entity;

import lombok.Getter;

@Getter
public class ClubReview {

    private Long clubId;

    private Long userId;

    private Double rating;

    private String content;
}
