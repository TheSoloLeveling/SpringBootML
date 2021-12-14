package com.example.demo.services;


import com.example.demo.entities.Activite;
import com.example.demo.repositories.ActiviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActiviteService {

    @Autowired
    ActiviteRepository activiteRepository;

    public List<Activite> showActivite() {
        return activiteRepository.findAll();
    }

}
