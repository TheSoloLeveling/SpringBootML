package com.example.demo.services;


import com.example.demo.entities.Activite;
import com.example.demo.entities.Affiliation;
import com.example.demo.repositories.ActiviteRepository;
import com.example.demo.repositories.AffiliationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AffiliationService {

    @Autowired
    AffiliationRepository affiliationRepository;

    public List<Affiliation> showActivite() {
        return affiliationRepository.findAll();
    }

}
