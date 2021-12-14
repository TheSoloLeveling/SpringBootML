package com.example.demo.controllers;

import com.example.demo.entities.Poste;
import com.example.demo.entities.Tresorerie;
import com.example.demo.services.PosteService;
import com.example.demo.services.TresorerieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path = "api/poste")
public class PosteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PosteController.class);

    @Autowired
    PosteService posteService;

    @GetMapping
    public List<Poste> showActivities(){
        return posteService.showActivite();
    }

}
