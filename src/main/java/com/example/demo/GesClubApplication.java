package com.example.demo;

import com.example.demo.entities.Club;
import com.example.demo.repositories.ClubRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import java.sql.Date;
import java.sql.Timestamp;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)

@ComponentScan({"com.example.demo.*"})
public class GesClubApplication {

    public static void main(String[] args) {
        SpringApplication.run(GesClubApplication.class, args);



    }

}
