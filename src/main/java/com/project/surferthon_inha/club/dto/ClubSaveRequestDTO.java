package com.project.surferthon_inha.club.dto;

import com.project.surferthon_inha.club.entity.Club;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class  ClubSaveRequestDTO {
    String clubName;

    String clubDescription;

    private Double rating;

    private Long ratingCount;

    public Club toEntity(Long userId){
        return new Club(this.clubName, this.clubDescription, this.rating, this.ratingCount, userId);
    }
}
