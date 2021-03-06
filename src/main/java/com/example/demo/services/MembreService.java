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
                                       , String email, String nameUser, String raison,
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

        if(findMember(club, EFonction.PRESIDENT) != null){
            emailService.sendEmail(
                    findMember(club, EFonction.PRESIDENT).getEmail(),
                    "New Member to your club",
                    membre.toString() + " \n Reason of joinning the club : \n" + raison);
        }

        emailService.sendEmail(
                email,
                "Join Club " + nomClub,
                "Your demand to join the club has been submited : \n " + membre.toString());

        Set<Membre> m = club.getMembres();
        m.add(membre);
        club.setMembres(m);

        clubRepository.save(club);

    }

    public List<Membre> findAllEMembersOfClub(String nameClub){

        List<Membre> eMembres = new LinkedList<>();
        Club club = clubRepository.findByNomClub(nameClub).get();
        Set<Membre> membres = club.getMembres();
        for (Membre m : membres){
            for (Fonctionnalite fonctionnalite : m.getFonctionnalites()){
                if(fonctionnalite.getName().equals(EFonction.PRESIDENT) ||
                        fonctionnalite.getName().equals(EFonction.VICEPRESIDENT) ||
                        fonctionnalite.getName().equals(EFonction.SECRETARY) ||
                        fonctionnalite.getName().equals(EFonction.TREASURER))
                {eMembres.add(m); break;}
            }
        }

        return eMembres;
    }

    public List<Membre> findAllAMembersOfClub(String nameClub){

        List<Membre> eMembres = new LinkedList<>();
        Club club = clubRepository.findByNomClub(nameClub).get();
        Set<Membre> membres = club.getMembres();
        for (Membre m : membres){
            for (Fonctionnalite fonctionnalite : m.getFonctionnalites()){
                if(fonctionnalite.getName().equals(EFonction.MEMBER))
                {eMembres.add(m); break;}
            }
        }

        return eMembres;
    }

    public List<Membre> findAllPMembersOfClub(String nameClub){

        List<Membre> eMembres = new LinkedList<>();
        Club club = clubRepository.findByNomClub(nameClub).get();
        Set<Membre> membres = club.getMembres();
        for (Membre m : membres){
            if(!m.isStatus())
            {eMembres.add(m); break;}
        }
        return eMembres;
    }

    public Membre acceptMembre(Integer idMembre){

        Membre membre = membreRepository.getById(idMembre);
        membre.setStatus(true);
        membreRepository.save(membre);
        return membre;
    }

    public void deleteMembre(Integer idMembre){

        Membre membre = membreRepository.getById(idMembre);
        Club club = clubRepository.getById(membre.getIdClub());
        Set<Fonctionnalite> f = membre.getFonctionnalites();
        f.clear();
        Set<Membre> membres = club.getMembres();
        membres.remove(membre);
        club.setMembres(membres);
        membre.setFonctionnalites(f);
        membreRepository.delete(membre);

        clubRepository.save(club);
    }

    public Membre findMember(Club c, EFonction e) {

        Set<Membre> membres = c.getMembres();

        for (Membre membre : membres) {
            Set<Fonctionnalite> fonctionnalites = membre.getFonctionnalites();
                for (Fonctionnalite fonctionnalite : fonctionnalites) {
                    if (fonctionnalite.getName().equals(e)){
                        System.out.println("Member with function : " + e.name() +" is found");
                        return membre;
                    }
                }
        }
        System.out.println(" Error: Member with function : " + e.name() + " not found");
        return null;
    }

    public Membre findMemberInClub(String nameCLub,String fonction) {

        EFonction e = null;
        if (fonction.equals("president"))
            e = EFonction.PRESIDENT;
        if (fonction.equals("vicepresident"))
            e = EFonction.VICEPRESIDENT;
        if (fonction.equals("secretaire"))
            e = EFonction.SECRETARY;
        if (fonction.equals("tresorier"))
            e = EFonction.TREASURER;
        Club c = clubService.getClubBynomClub(nameCLub).getBody();
        Set<Membre> membres = c.getMembres();

        for (Membre membre : membres) {
            Set<Fonctionnalite> fonctionnalites = membre.getFonctionnalites();
            for (Fonctionnalite fonctionnalite : fonctionnalites) {
                if (fonctionnalite.getName().equals(e)){
                    System.out.println("Member with function : " + e.name() +" is found");
                    return membre;
                }
            }
        }
        System.out.println(" Error: Member with function : " + e.name() + " not found");
        return null;
    }

    public Membre checkIsMember(String nameClub, Long idUser) {
        Club club = clubService.getClubBynomClub(nameClub).getBody();
        if(findMember(idUser, club.getIdClub()) != null)
            return findMember(idUser, club.getIdClub());
        else
            return null;
    }

    public boolean isPresidenOfClub(String nameClub, Long id) {

        Membre m = findMember(clubService.getClubBynomClub(nameClub).getBody(), EFonction.PRESIDENT);
        if(m != null)
            return m.getIdUser().equals(id);
        return false;
    }

    public Membre PresidenOfClub(String nameClub, Long id) {

        Membre m = findMember(clubService.getClubBynomClub(nameClub).getBody(), EFonction.PRESIDENT);
        if(m != null)
            if (m.getIdUser().equals(id))
                return m;
        return null;
    }

    public boolean isEMember(String nameClub, Long id) {

        Membre president = findMember(clubService.getClubBynomClub(nameClub).getBody(), EFonction.PRESIDENT);
        Membre vicePresident = findMember(clubService.getClubBynomClub(nameClub).getBody(), EFonction.VICEPRESIDENT);
        Membre secretaire = findMember(clubService.getClubBynomClub(nameClub).getBody(), EFonction.SECRETARY);
        Membre tresorier = findMember(clubService.getClubBynomClub(nameClub).getBody(), EFonction.TREASURER);

        if(president != null && vicePresident != null &&  secretaire != null && tresorier != null )
            return president.getIdUser().equals(id) || vicePresident.getIdUser().equals(id) ||
                    tresorier.getIdUser().equals(id) || secretaire.getIdUser().equals(id);
        return false;
    }


    public List<Club> findAllClubsJoinedWithFilter(Long id, String field, boolean order) {

        List<Club> op = new ArrayList<>();
        List<Membre> m = membreRepository.findAllByIdUser(id);
        System.out.println(m);
        for (Membre membre : m) {
            op.add(clubService.getClubById(membre.getIdClub()).getBody());
        }

        if (field.equals("nomClub") && order)
            op.sort(Comparator.comparing(Club::getNomClub));
        if (field.equals("nomClub") && !order)
            op.sort(Comparator.comparing(Club::getNomClub).reversed());
        if (field.equals("dateCre") && order)
            op.sort(Comparator.comparing(Club::getDateCre));
        if (field.equals("dateCre") && !order)
            op.sort(Comparator.comparing(Club::getDateCre).reversed());
        if (field.equals("nbrFollowers") && order)
            op.sort(Comparator.comparing(Club::getNbrFollowers));
        if (field.equals("nbrFollowers") && !order)
            op.sort(Comparator.comparing(Club::getNbrFollowers).reversed());

        return op;
    }

    public Membre findMember(Long idUser, Integer idClub) {
        return membreRepository.findByIdClubAndIdUser(idClub, idUser);
    }
    public Membre findMember(String nameUser, Integer idClub) {
        return membreRepository.findByIdClubAndNameUser(idClub, nameUser);
    }
    public String findMemberRole(Long idUser, Integer idClub) {
        Membre m = findMember(idUser, idClub);
        for (Fonctionnalite fonctionnalite : m.getFonctionnalites())
            return fonctionnalite.getName().name();
        return null;
    }

    public ArrayList<Membre> retrieveAllUserDetails(){
        return (ArrayList<Membre>) membreRepository.findAll();
    }

    public Optional<Membre> getUserData(Integer idMembre) {
        return membreRepository.findById(idMembre);
    }




}
