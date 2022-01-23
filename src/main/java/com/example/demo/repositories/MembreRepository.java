package com.example.demo.repositories;


import com.example.demo.entities.Membre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembreRepository extends JpaRepository<Membre, Integer> {


}
