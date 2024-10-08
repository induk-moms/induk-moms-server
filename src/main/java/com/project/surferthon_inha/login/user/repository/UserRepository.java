package com.project.surferthon_inha.login.user.repository;

import com.project.surferthon_inha.login.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findBySub(String sub);
}
