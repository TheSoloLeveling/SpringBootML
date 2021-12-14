package com.example.demo.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String idCate;

    private String nomCate;
    private String descCate;

    @OneToMany(mappedBy = "categorie")
    private List<Club> clubs;

    public String getIdCate() {
        return idCate;
    }

    public String getNomCate() {
        return nomCate;
    }

    public String getDescCate() {
        return descCate;
    }

    public List<Club> getClubs() {
        return clubs;
    }
}
