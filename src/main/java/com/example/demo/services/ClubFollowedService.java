package com.example.demo.services;


import com.example.demo.repositories.ClubFollowedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClubFollowedService {

    @Autowired
    ClubFollowedRepository clubFollowedRepository;
}
