package com.example.demo.repositories;

import com.example.demo.entities.EFonction;
import com.example.demo.entities.ERole;
import com.example.demo.entities.Fonctionnalite;
import com.example.demo.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FonctionnaliteRepository extends JpaRepository<Fonctionnalite, Integer> {

    Optional<Fonctionnalite> findByName(EFonction name);
}
