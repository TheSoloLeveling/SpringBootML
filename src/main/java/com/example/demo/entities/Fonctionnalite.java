package com.example.demo.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Fonctionnalite {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idFonct;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EFonction name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(  name = "member_fonctionnalities",
            joinColumns = @JoinColumn(name = "membre_id"),
            inverseJoinColumns = @JoinColumn(name = "fonction_id"))
    private Membre membre;


    public Fonctionnalite() {
    }

    public Fonctionnalite(EFonction name) {
        this.name = name;
    }

    public void setIdFonct(Integer idFonct) {
        this.idFonct = idFonct;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Membre getMembre() {
        return membre;
    }

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
