package com.example.demo.services;


import com.example.demo.entities.Poste;
import com.example.demo.entities.Tresorerie;
import com.example.demo.repositories.PosteRepository;
import com.example.demo.repositories.TresorerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PosteService {

    @Autowired
    PosteRepository posteRepository;

    public List<Poste> showActivite() {
        return posteRepository.findAll();
    }

}
