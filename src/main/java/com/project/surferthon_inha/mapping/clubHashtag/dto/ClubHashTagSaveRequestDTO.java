package com.project.surferthon_inha.mapping.clubHashtag.dto;

import com.project.surferthon_inha.hashTag.entity.HashTag;
import lombok.Data;

import java.util.List;

@Data
public class ClubHashTagSaveRequestDTO {

    private Long clubId;

    private List<HashTag> hashTagList;
}
