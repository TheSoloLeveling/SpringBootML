package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
public class Membre {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "idMembre", unique = true, nullable = false)
    private Integer idMembre;

    private String nom;
    private String filiere;
    private int anneeE;
    private boolean status;
    private Date dateCon;
    private Date dateCre;
    private String pdpMembre;
    private String email;

    @Column(name = "club_id", unique = true, nullable = false)
    private Integer idClub;

    @Override
    public String toString() {
        return "Membre{" +
                "idMembre=" + idMembre +
                ", nom='" + nom + '\'' +
                ", filiere='" + filiere + '\'' +
                ", anneeE=" + anneeE +
                ", status=" + status +
                ", dateCon=" + dateCon +
                ", dateCre=" + dateCre +
                ", pdpMembre='" + pdpMembre + '\'' +
                ", email='" + email + '\'' +
                ", idClub=" + idClub +
                ", idUser=" + idUser +
                ", nameUser='" + nameUser + '\'' +
                ", fonctionnalites=" + fonctionnalites +
                '}';
    }

    public Integer getIdClub() {
        return idClub;
    }

    public void setIdClub(Integer idClub) {
        this.idClub = idClub;
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

    public Membre(String nom, String filiere, int anneeE, String email, String nameUser) {
        this.nom = nom;
        this.filiere = filiere;
        this.anneeE = anneeE;
        this.email = email;
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(  name = "membre_fonction",
            joinColumns = @JoinColumn(name = "membre_id"),
            inverseJoinColumns = @JoinColumn(name = "fonction_id"))
    private Set<Fonctionnalite> fonctionnalites;

    public Integer getIdMembre() {
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

    public void setIdMembre(Integer idMembre) {
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


    public void setFonctionnalites(Set<Fonctionnalite> fonctionnalites) {
        this.fonctionnalites = fonctionnalites;
    }

}
