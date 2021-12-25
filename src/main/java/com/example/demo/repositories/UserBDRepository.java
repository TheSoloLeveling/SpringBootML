package com.example.demo.repositories;

import com.example.demo.entities.UserBD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserBDRepository extends JpaRepository<UserBD, Long> {
    Optional<UserBD> findByuserName(String username);

    Boolean existsByuserName(String username);

}
