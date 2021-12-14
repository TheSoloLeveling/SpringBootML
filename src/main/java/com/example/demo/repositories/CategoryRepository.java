package com.example.demo.repositories;


import com.example.demo.entities.Categorie;
import com.example.demo.entities.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Categorie, String> {
}
