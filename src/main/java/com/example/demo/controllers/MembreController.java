package com.example.demo.controllers;

import com.example.demo.entities.Club;
import com.example.demo.entities.Membre;
import com.example.demo.entities.RequestCreateClub;
import com.example.demo.entities.RequestCreateMembre;
import com.example.demo.services.MembreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/memberService")
public class MembreController {

    @Autowired
    MembreService membreService;


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



}
