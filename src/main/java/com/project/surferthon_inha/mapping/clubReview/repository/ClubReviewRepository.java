package com.project.surferthon_inha.mapping.clubReview.repository;

import com.project.surferthon_inha.mapping.clubHashtag.entity.ClubHashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubReviewRepository extends JpaRepository<ClubHashTag, Long> {
}
