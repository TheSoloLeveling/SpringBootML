package com.example.demo.entities;

import java.util.Date;

public class RequestCreateMembre {

    private String nom;
    private String filiere;
    private int anneeE;
    private String email;
    private String nameUser;
    private String raison;
    private String nomClub;

    @Override
    public String toString() {
        return "RequestCreateMembre{" +
                "nom='" + nom + '\'' +
                ", filiere='" + filiere + '\'' +
                ", anneeE=" + anneeE +
                ", email='" + email + '\'' +
                ", nameUser='" + nameUser + '\'' +
                ", raison='" + raison + '\'' +
                ", nomClub='" + nomClub + '\'' +
                '}';
    }

    public RequestCreateMembre(String nom, String filiere, int anneeE, String email, String nameUser, String raison, String nomClub) {
        this.nom = nom;
        this.filiere = filiere;
        this.anneeE = anneeE;
        this.email = email;
        this.nameUser = nameUser;
        this.raison = raison;
        this.nomClub = nomClub;
    }

    public void setNomClub(String nomClub) {
        this.nomClub = nomClub;
    }

    public String getNomClub() {
        return nomClub;
    }

    public RequestCreateMembre() {
    }

    public RequestCreateMembre(String nom, String filiere, int anneeE, String email, String nameUser, String raison) {
        this.nom = nom;
        this.filiere = filiere;
        this.anneeE = anneeE;
        this.email = email;
        this.nameUser = nameUser;
        this.raison = raison;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    public void setAnneeE(int anneeE) {
        this.anneeE = anneeE;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    public String getNom() {
        return nom;
    }

    public String getFiliere() {
        return filiere;
    }

    public int getAnneeE() {
        return anneeE;
    }

    public String getEmail() {
        return email;
    }

    public String getNameUser() {
        return nameUser;
    }

    public String getRaison() {
        return raison;
    }
}
