package com.project.surferthon_inha.club.service;

import com.project.surferthon_inha.club.dto.ClubSaveRequestDTO;
import com.project.surferthon_inha.club.entity.Club;
import com.project.surferthon_inha.club.repository.ClubRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Service
public class ClubService {
    private final ClubRepository clubRepository;

    public ClubService(ClubRepository clubRepository){
        this.clubRepository = clubRepository;
    }

    public void saveClub(ClubSaveRequestDTO clubSaveRequestDTO, Long userId) {
        clubRepository.save(clubSaveRequestDTO.toEntity(userId));
    }

    public Club findClub(String clubName){
        return clubRepository.findClubByName(clubName).orElseGet(null);
    }

    public List<Club> findAllClub(){
        return clubRepository.findAll();
    }
}
