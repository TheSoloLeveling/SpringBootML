package com.example.demo.controllers;

import com.example.demo.entities.Affiliation;
import com.example.demo.entities.Tresorerie;
import com.example.demo.services.AffiliationService;
import com.example.demo.services.TresorerieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path = "api/tresorerie")
public class TresorerieController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TresorerieController.class);

    @Autowired
    TresorerieService tresorerieService;

    @GetMapping
    public List<Tresorerie> showActivities(){
        return tresorerieService.showActivite();
    }

}
