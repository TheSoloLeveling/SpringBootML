package com.example.demo.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Referent {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String idRef;

    private String nomRef;
    private String prenRef;
    private boolean status;

    @OneToMany(mappedBy = "referent")
    private List<Club> clubs;

    public String getIdRef() {
        return idRef;
    }

    public String getNomRef() {
        return nomRef;
    }

    public String getPrenRef() {
        return prenRef;
    }

    public boolean isStatus() {
        return status;
    }

    public List<Club> getClubs() {
        return clubs;
    }
}
