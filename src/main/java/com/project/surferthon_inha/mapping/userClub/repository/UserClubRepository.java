package com.project.surferthon_inha.mapping.userClub.repository;

import com.project.surferthon_inha.mapping.clubHashtag.entity.ClubHashTag;
import com.project.surferthon_inha.mapping.userClub.entity.UserClub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserClubRepository extends JpaRepository<UserClub, Long> {
}
