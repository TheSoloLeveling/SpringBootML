package com.example.demo;

import com.example.demo.entities.Club;
import com.example.demo.repositories.ClubRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.sql.Date;
import java.sql.Timestamp;

@SpringBootApplication
@ComponentScan({"com.example.demo.*"})
public class GesClubApplication {

    public static void main(String[] args) {
        SpringApplication.run(GesClubApplication.class, args);



    }

}
