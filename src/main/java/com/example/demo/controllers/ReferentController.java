package com.example.demo.controllers;

import com.example.demo.entities.Referent;
import com.example.demo.entities.Tresorerie;
import com.example.demo.services.ReferentService;
import com.example.demo.services.TresorerieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path = "api/referent")
public class ReferentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReferentController.class);

    @Autowired
    ReferentService referentService;

    @GetMapping
    public List<Referent> showActivities(){
        return referentService.showActivite();
    }

}
