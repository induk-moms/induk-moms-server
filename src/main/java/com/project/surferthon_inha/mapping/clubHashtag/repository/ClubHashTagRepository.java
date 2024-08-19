package com.project.surferthon_inha.mapping.clubHashtag.repository;

import com.project.surferthon_inha.mapping.clubHashtag.entity.ClubHashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubHashTagRepository extends JpaRepository<ClubHashTag, Long> {
}
