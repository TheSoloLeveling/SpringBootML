package com.example.demo.repositories;


import com.example.demo.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Integer> {

    Optional<Club> findByNomClub(String nomClub);
    Optional<Club> findByIdClub(Integer idClub);


}
