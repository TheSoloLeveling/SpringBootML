package com.example.demo.repositories;


import com.example.demo.entities.Club;
import com.example.demo.entities.Membre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MembreRepository extends JpaRepository<Membre, Integer> {

    Optional<Membre> findByNameUser(String nameUser);

    List<Membre> findAllByNameUser(String nameUser);

    List<Membre> findAllByIdUser(Long id);

    Membre findByIdClubAndIdUser(Integer idClub, Long id);

}
