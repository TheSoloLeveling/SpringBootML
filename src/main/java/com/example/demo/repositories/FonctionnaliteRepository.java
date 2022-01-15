package com.example.demo.repositories;

import com.example.demo.entities.EFonction;
import com.example.demo.entities.ERole;
import com.example.demo.entities.Fonctionnalite;
import com.example.demo.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FonctionnaliteRepository extends JpaRepository<Role, Integer> {
    Optional<Fonctionnalite> findByName(EFonction name);
}
