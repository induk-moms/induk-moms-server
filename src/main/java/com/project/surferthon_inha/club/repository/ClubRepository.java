package com.project.surferthon_inha.club.repository;


import com.project.surferthon_inha.club.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {

    Optional<Club> findClubByName(String Name);
}
