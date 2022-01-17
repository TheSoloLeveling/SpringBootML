package com.example.demo.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Referent {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String idRef;

    private String nomRef;
    private String email;
    private Long idUser;
    private String nameUser;
    private String filiere;



    public void setIdRef(String idRef) {
        this.idRef = idRef;
    }

    public void setNomRef(String nomRef) {
        this.nomRef = nomRef;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    public String getEmail() {
        return email;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public Long getIdUser() {
        return idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public Referent(String nomRef, String filiere, String email, String nameUser) {
        this.nomRef = nomRef;
        this.filiere = filiere;
        this.email = email;
        this.nameUser = nameUser;
    }

    public Referent() {
    }

    private boolean status;

    @OneToMany(mappedBy = "referent")
    private List<Club> clubs;

    public String getIdRef() {
        return idRef;
    }

    public String getNomRef() {
        return nomRef;
    }

    public String getFiliere() {
        return filiere;
    }

    public boolean isStatus() {
        return status;
    }

    public List<Club> getClubs() {
        return clubs;
    }
}
