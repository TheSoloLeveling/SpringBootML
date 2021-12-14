package com.example.demo.repositories;


import com.example.demo.entities.Affiliation;
import com.example.demo.entities.Tresorerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TresorerieRepository extends JpaRepository<Tresorerie, String> {
}
