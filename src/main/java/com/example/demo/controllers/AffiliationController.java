package com.example.demo.controllers;

import com.example.demo.entities.Activite;
import com.example.demo.entities.Affiliation;
import com.example.demo.services.ActiviteService;
import com.example.demo.services.AffiliationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path = "api/affiliation")
public class AffiliationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AffiliationController.class);

    @Autowired
    AffiliationService affiliationService;

    @GetMapping
    public List<Affiliation> showActivities(){
        return affiliationService.showActivite();
    }

}
