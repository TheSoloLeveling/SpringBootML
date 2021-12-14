package com.example.demo.repositories;


import com.example.demo.entities.Activite;
import com.example.demo.entities.Affiliation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AffiliationRepository extends JpaRepository<Affiliation, String> {
}
