package com.example.demo.services;


import com.example.demo.Mail.EmailService;
import com.example.demo.entities.*;
import com.example.demo.repositories.ClubRepository;
import com.example.demo.repositories.FonctionnaliteRepository;
import com.example.demo.repositories.MembreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class MembreService {

    @Autowired
    private MembreRepository membreRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserBDService userBDService;

    @Autowired
    private ClubService clubService;

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private FonctionnaliteRepository fonctionnaliteRepository;


    public void submitMetaDataOfUser(String nom, String filiere, int anneeE
                                       , String email, String nameUser, String Raison,
                                       String nomClub) {

        Date date=new Date();
        long time=date.getTime();
        Timestamp dateTime=new Timestamp(time);
        Membre membre = new Membre();
        membre.setStatus(false);
        membre.setDateCre(dateTime);
        membre.setNameUser(nameUser);
        membre.setAnneeE(anneeE);
        membre.setEmail(email);
        membre.setNom(nom);
        membre.setFiliere(filiere);

        Club club = clubService.getClubBynomClub(nomClub).getBody();

        Long id = userBDService.getUserByusername(membre.getNameUser()).getBody().getId();
        membre.setIdUser(id);

        Set<Fonctionnalite> fonctionsM = new HashSet<>();
        Fonctionnalite fonctionnaliteT = fonctionnaliteRepository.findByName(EFonction.MEMBER)
                .orElseThrow(() -> new RuntimeException("Error: Function is not found."));
        fonctionsM.add(fonctionnaliteT);
        membre.setFonctionnalites(fonctionsM);

       // emailService.sendEmail(club.findMember(EFonction.PRESIDENT).getEmail(),
        //        "New Member to your club",
         //       membre.toString());

        Set<Membre> m = club.getMembres();
        m.add(membre);
        club.setMembres(m);


        clubRepository.save(club);

    }



    public ArrayList<Membre> retrieveAllUserDetails(){
        return (ArrayList<Membre>) membreRepository.findAll();
    }

    public Optional<Membre> getUserData(Integer idMembre) {
        return membreRepository.findById(idMembre);
    }




}
