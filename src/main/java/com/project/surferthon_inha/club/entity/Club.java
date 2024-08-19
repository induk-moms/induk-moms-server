package com.project.surferthon_inha.club.entity;


import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Club {

    @Id
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private Double rating;

    @NotNull
    private Long ratingCount;

    @NotNull
    private Long userId;

    public Club(String name, String description, Double rating, Long ratingCount, Long userId) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.userId = userId;
    }
}
