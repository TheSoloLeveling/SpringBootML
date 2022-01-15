package com.example.demo.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Fonctionnalite {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idFonct;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EFonction name;

    public Fonctionnalite(EFonction name) {
        this.name = name;
    }

    public Fonctionnalite() {
    }

    /*@ManyToMany
    @JoinTable(
            name = "MembreClub",
            joinColumns = @JoinColumn(name = "fonctionnalite_id"),
            inverseJoinColumns = @JoinColumn(name = "membre_id")
    )
    private List<Membre> membres;*/

    public Integer getIdFonct() {
        return idFonct;
    }

    public void setName(EFonction name) {
        this.name = name;
    }

    public EFonction getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Fonctionnalite{" +
                "idFonct=" + idFonct +
                ", name=" + name +
                '}';
    }
}
