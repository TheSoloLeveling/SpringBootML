package com.example.demo.repositories;

import com.example.demo.entities.UserBD;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserBD, Integer> {
    Optional<UserBD> findByUserName(String userName);
}
