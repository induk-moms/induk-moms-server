package com.project.surferthon_inha.hashTag.entity;


import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
public class HashTag {

    @Id
    private String name;
}
