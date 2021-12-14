package com.example.demo.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Affiliation {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String idAff;

    private String nomAff;
    private String DescAff;

    @OneToMany(mappedBy = "affiliation")
    private List<Club> clubs;

    public String getIdAff() {
        return idAff;
    }

    public String getNomAff() {
        return nomAff;
    }

    public String getDescAff() {
        return DescAff;
    }

    public List<Club> getClubs() {
        return clubs;
    }
}
