package com.example.demo.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
public class Membre {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String idMembre;
    private String nom;
    private String filiere;
    private int anneeE;
    private boolean status;
    private Date dateCon;
    private Date dateCre;
    private String pdpMembre;
    private String email;

    @Override
    public String toString() {
        return "Membre{" +
                "nom='" + nom + '\'' +
                ", filiere='" + filiere + '\'' +
                ", anneeE=" + anneeE +
                ", dateCre=" + dateCre +
                ", email='" + email + '\'' +
                ", fonctionnalites=" + fonctionnalites +
                '}';
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    private Long idUser;
    private String nameUser;

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public Membre(String nom, String filiere, int anneeE, String nameUser) {
        this.nom = nom;
        this.filiere = filiere;
        this.anneeE = anneeE;
        this.nameUser = nameUser;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    public void setAnneeE(int anneeE) {
        this.anneeE = anneeE;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getFiliere() {
        return filiere;
    }

    public int getAnneeE() {
        return anneeE;
    }

    public Long getIdUser() {
        return idUser;
    }

    public Membre() {
    }

    @ManyToOne
    @JoinTable(
            name = "MembreClub",
            joinColumns = @JoinColumn(name = "membre_id"),
            inverseJoinColumns = @JoinColumn(name = "club_id")
    )
    private Club club;

    @ManyToOne
    @JoinColumn(name="groupe_id")
    private Groupe groupe;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "member_fonctionnalities",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "fonctionalities_id"))
    private Set<Fonctionnalite> fonctionnalites;

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

    public Club getClub() {
        return club;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public Set<Fonctionnalite> getFonctionnalites() {
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

    public void setClub(Club clubs) {
        this.club = clubs;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public void setFonctionnalites(Set<Fonctionnalite> fonctionnalites) {
        this.fonctionnalites = fonctionnalites;
    }
}
