package com.example.demo.controllers;

import com.example.demo.entities.Affiliation;
import com.example.demo.entities.Membre;
import com.example.demo.services.AffiliationService;
import com.example.demo.services.MembreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path = "api/membre")
public class MembreController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MembreController.class);

    @Autowired
    MembreService membreService;

    @GetMapping
    public List<Membre> showActivities(){
        return membreService.showActivite();
    }



}
