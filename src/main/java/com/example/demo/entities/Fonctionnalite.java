package com.example.demo.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Fonctionnalite {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String idFonct;

    private String nomFonct;
    private String descFonct;

    @ManyToMany
    @JoinTable(
            name = "MembreClub",
            joinColumns = @JoinColumn(name = "fonctionnalite_id"),
            inverseJoinColumns = @JoinColumn(name = "membre_id")
    )
    private List<Membre> membres;

    public String getIdFonct() {
        return idFonct;
    }

    public String getNomFonct() {
        return nomFonct;
    }

    public String getDescFonct() {
        return descFonct;
    }
}
