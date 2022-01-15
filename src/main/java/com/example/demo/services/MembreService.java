package com.example.demo.services;


import com.example.demo.Mail.EmailService;
import com.example.demo.entities.Club;
import com.example.demo.entities.EFonction;
import com.example.demo.entities.Fonctionnalite;
import com.example.demo.entities.Membre;
import com.example.demo.repositories.ClubRepository;
import com.example.demo.repositories.MembreRepository;
import org.springframework.beans.factory.annotation.Autowired;
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


    public Membre submitMetaDataOfUser(Membre membre, String nomClub) {

        Date date=new Date();
        long time=date.getTime();
        Timestamp dateTime=new Timestamp(time);

        membre.setStatus(true);
        membre.setDateCre(dateTime);

        Club club = clubService.getClubBynomClub(nomClub).getBody();

        Long id = userBDService.getUserByusername(membre.getNameUser()).getBody().getId();
        membre.setIdUser(id);

        Set<Fonctionnalite> f = new HashSet<>();
        f.add(new Fonctionnalite(EFonction.MEMBER));
        membre.setFonctionnalites(f);

        emailService.sendEmail(club.findMember(EFonction.PRESIDENT).getEmail(),
                "New Member to your club",
                membre.toString());

        LinkedList<Membre> m = club.getMembres();
        m.add(membre);
        club.setMembres(m);
        membre.setClub(club);
        clubRepository.save(club);
        
        return membreRepository.save(membre);
    }

    public ArrayList<Membre> retrieveAllUserDetails(){
        return (ArrayList<Membre>) membreRepository.findAll();
    }

    public Optional<Membre> getUserData(String idMembre) {
        return membreRepository.findById(idMembre);
    }



}
