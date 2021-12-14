package com.example.demo.services;


import com.example.demo.entities.Referent;
import com.example.demo.entities.Tresorerie;
import com.example.demo.repositories.ReferentRepository;
import com.example.demo.repositories.TresorerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferentService {

    @Autowired
    ReferentRepository referentRepository;

    public List<Referent> showActivite() {
        return referentRepository.findAll();
    }

}
