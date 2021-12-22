package com.example.demo.services;


import com.example.demo.entities.Membre;
import com.example.demo.repositories.MembreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class MembreService {

    @Autowired
    MembreRepository membreRepository;


    public Membre submitMetaDataOfUser(Membre membre) {

        Date date=new Date();
        long time=date.getTime();
        Timestamp dateTime=new Timestamp(time);

        membre.setStatus(true);
        membre.setDateCre(dateTime);

        return membreRepository.save(membre);
    }

    public ArrayList<Membre> retrieveAllUserDetails(){
        return (ArrayList<Membre>) membreRepository.findAll();
    }

    public Optional<Membre> getUserData(String idMembre) {
        return membreRepository.findById(idMembre);
    }


}
