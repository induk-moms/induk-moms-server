package com.project.surferthon_inha.mapping.clubHashtag.service;

import com.project.surferthon_inha.login.jwt.entity.UserInformInToken;
import com.project.surferthon_inha.mapping.clubHashtag.dto.ClubHashTagSaveRequestDTO;
import com.project.surferthon_inha.mapping.clubHashtag.entity.ClubHashTag;
import com.project.surferthon_inha.mapping.clubHashtag.repository.ClubHashTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubHashTagService {

    private final ClubHashTagRepository clubHashTagRepository;

    public void saveClubHashTag(UserInformInToken userInform, ClubHashTagSaveRequestDTO clubHashTagSaveRequestDTO) {
    }
}
