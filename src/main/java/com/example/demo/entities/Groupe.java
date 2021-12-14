package com.example.demo.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Groupe {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String idGrp;

    private String nomGrp;
    private int valGrp;
    private String descGrp;
    private boolean status;

    @OneToMany(mappedBy = "groupe")
    private List<Membre> membres;

    public String getIdGrp() {
        return idGrp;
    }

    public String getNomGrp() {
        return nomGrp;
    }

    public int getValGrp() {
        return valGrp;
    }

    public String getDescGrp() {
        return descGrp;
    }

    public boolean isStatus() {
        return status;
    }
}
