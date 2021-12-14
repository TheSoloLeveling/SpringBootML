package com.example.demo.controllers;

import com.example.demo.entities.Categorie;
import com.example.demo.entities.Club;
import com.example.demo.services.CategoryService;
import com.example.demo.services.ClubService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path = "api/category")
public class CategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public List<Categorie> showActivities(){
        return categoryService.showActivite();
    }

}
