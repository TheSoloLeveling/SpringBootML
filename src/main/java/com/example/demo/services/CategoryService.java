package com.example.demo.services;


import com.example.demo.entities.Categorie;
import com.example.demo.entities.Club;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Categorie> showActivite() {
        return categoryRepository.findAll();
    }

}
