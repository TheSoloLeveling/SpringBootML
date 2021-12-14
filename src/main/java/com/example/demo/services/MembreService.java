package com.example.demo.services;


import com.example.demo.entities.Affiliation;
import com.example.demo.entities.Membre;
import com.example.demo.repositories.AffiliationRepository;
import com.example.demo.repositories.MembreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembreService {

    @Autowired
    MembreRepository membreRepository;

    public List<Membre> showActivite() {
        return membreRepository.findAll();
    }


}
