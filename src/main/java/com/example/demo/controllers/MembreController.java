package com.example.demo.controllers;

import com.example.demo.entities.Club;
import com.example.demo.entities.Fonctionnalite;
import com.example.demo.entities.Membre;
import com.example.demo.entities.RequestCreateMembre;
import com.example.demo.repositories.MembreRepository;
import com.example.demo.services.MembreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/memberService")
public class MembreController {

    @Autowired
    MembreService membreService;

    @Autowired
    MembreRepository membreRepository;


    @PostMapping(value = "/save", headers = {
            "content-type=application/json" }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveUserMetaData(@RequestBody RequestCreateMembre r) {
         membreService.submitMetaDataOfUser(r.getNom(), r.getFiliere(), r.getAnneeE(),
                r.getEmail(), r.getNameUser(), r.getRaison(), r.getNomClub());
    }


    @GetMapping("/getUserDetails")
    public ArrayList<Membre> getAllUserDetails() {
        return membreService.retrieveAllUserDetails();
    }

    @GetMapping("/getAllUsers/{idMembre}")
    public Optional<Membre> getUserDetail(@PathVariable("idMembre") Integer userID) {
        return membreService.getUserData(userID);
    }

    @GetMapping("/findAllEMembersOfClub/{nameClub}")
    public List<Membre> findAllEMembersOfClub(@PathVariable("nameClub") String nameClub) {
        return membreService.findAllEMembersOfClub(nameClub);
    }

    @GetMapping("/findAllPMembersOfClub/{nameClub}")
    public List<Membre> findAllPMembersOfClub(@PathVariable("nameClub") String nameClub) {
        return membreService.findAllPMembersOfClub(nameClub);
    }

    @GetMapping("/acceptMembre/{idMembre}")
    public Membre acceptMembre(@PathVariable("idMembre") Integer idMembre) {
        return membreService.acceptMembre(idMembre);
    }

    @DeleteMapping("/delete/{idMembre}")
    public void delete(@PathVariable("idMembre") Integer idMembre) {
        membreService.deleteMembre(idMembre);
    }

    @GetMapping("/findAllAMembersOfClub/{nameClub}")
    public List<Membre> findAllAMembersOfClub(@PathVariable("nameClub") String nameClub) {
        return membreService.findAllAMembersOfClub(nameClub);
    }

    @GetMapping("/test/{idClub}")
    public Membre getUserDetail(@RequestParam int idClub) {
        return null;
    }

    @GetMapping("/checkIsMember/{idUser}/{nameClub}")
    public Membre checkIsMember(@PathVariable("idUser") Long idUser, @PathVariable("nameClub") String nameClub) {
        return membreService.checkIsMember(nameClub, idUser);
    }

    @GetMapping("/findClubJoined/{idUser}/{field}/{order}")
    public List<Club> findClubFollowed(@PathVariable Long idUser, @PathVariable String field, @PathVariable boolean order) {
        return membreService.findAllClubsJoinedWithFilter(idUser, field, order);
    }

    @GetMapping("/checkPresident/{nameClub}/{idUser}")
    public boolean checkPresidentOfClub(@PathVariable String nameClub, @PathVariable Long idUser) {
        return membreService.isPresidenOfClub(nameClub, idUser);
    }

    @GetMapping("/President/{nameClub}/{idUser}")
    public Membre PresidentOfClub(@PathVariable String nameClub, @PathVariable Long idUser) {
        return membreService.PresidenOfClub(nameClub, idUser);
    }

    @GetMapping("/checkEMember/{nameClub}/{idUser}")
    public boolean checkEMember(@PathVariable String nameClub, @PathVariable Long idUser) {
        return membreService.isEMember(nameClub, idUser);
    }

    @GetMapping("/findMemberRole/{idClub}/{idUser}")
    public String findMemberRole(@PathVariable Integer idClub, @PathVariable Long idUser) {
        return membreService.findMemberRole(idUser, idClub);
    }


    @GetMapping("/findMemberInClub/{nameClub}/{f}")
    public Membre findMemberInClub(@PathVariable String nameClub, @PathVariable String f) {
        return membreService.findMemberInClub(nameClub, f);
    }

}
