package com.example.demo.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Membre {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String idMembre;
    private String nom;
    private boolean status;
    private Date dateCon;
    private Date dateCre;
    private String pdpMembre;


    private Long idUser;

    @ManyToMany
    @JoinTable(
            name = "MembreClub",
            joinColumns = @JoinColumn(name = "membre_id"),
            inverseJoinColumns = @JoinColumn(name = "club_id")
    )
    private List<Club> clubs;

    @ManyToOne
    @JoinColumn(name="groupe_id")
    private Groupe groupe;

    @ManyToMany(mappedBy = "membres")
    private List<Fonctionnalite> fonctionnalites;

    public String getIdMembre() {
        return idMembre;
    }

    public boolean isStatus() {
        return status;
    }

    public Date getDateCon() {
        return dateCon;
    }

    public String getPdpMembre() {
        return pdpMembre;
    }

    public List<Club> getClubs() {
        return clubs;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public List<Fonctionnalite> getFonctionnalites() {
        return fonctionnalites;
    }

    public Date getDateCre() {
        return dateCre;
    }

    public void setDateCre(Date dateCre) {
        this.dateCre = dateCre;
    }

    public String getNom() {
        return nom;
    }

    public void setIdMembre(String idMembre) {
        this.idMembre = idMembre;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setDateCon(Date dateCon) {
        this.dateCon = dateCon;
    }

    public void setPdpMembre(String pdpMembre) {
        this.pdpMembre = pdpMembre;
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public void setFonctionnalites(List<Fonctionnalite> fonctionnalites) {
        this.fonctionnalites = fonctionnalites;
    }
}
