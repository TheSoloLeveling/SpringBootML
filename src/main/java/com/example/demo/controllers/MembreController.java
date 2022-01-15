package com.example.demo.controllers;

import com.example.demo.entities.Membre;
import com.example.demo.services.MembreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/memberService")
public class MembreController {

    @Autowired
    MembreService membreService;

    @PostMapping("/save")
    public Membre saveUserMetaData(@RequestBody Membre user, String nomClub) {
        return membreService.submitMetaDataOfUser(user, nomClub);
    }

    @GetMapping("/getUserDetails")
    public ArrayList<Membre> getAllUserDetails() {
        return membreService.retrieveAllUserDetails();
    }

    @GetMapping("/getAllUsers/{idMembre}")
    public Optional<Membre> getUserDetail(@PathVariable("idMembre") String userID) {
        return membreService.getUserData(userID);
    }



}
