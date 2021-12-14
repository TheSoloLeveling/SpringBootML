package com.example.demo.services;


import com.example.demo.entities.Affiliation;
import com.example.demo.entities.Tresorerie;
import com.example.demo.repositories.AffiliationRepository;
import com.example.demo.repositories.TresorerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TresorerieService {

    @Autowired
    TresorerieRepository tresorerieRepository;

    public List<Tresorerie> showActivite() {
        return tresorerieRepository.findAll();
    }

}
