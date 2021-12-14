package com.example.demo.controllers;

import com.example.demo.entities.Activite;
import com.example.demo.services.ActiviteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ActiviteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActiviteController.class);

    @Autowired
    ActiviteService activiteService;




}
