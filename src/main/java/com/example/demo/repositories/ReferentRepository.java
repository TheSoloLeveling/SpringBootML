package com.example.demo.repositories;


import com.example.demo.entities.Referent;
import com.example.demo.entities.Tresorerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferentRepository extends JpaRepository<Referent, String> {
}
