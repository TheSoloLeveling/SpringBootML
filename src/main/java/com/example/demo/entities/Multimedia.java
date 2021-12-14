package com.example.demo.entities;

import javax.persistence.*;

@Entity
public class Multimedia {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String idMult;

    private String url;

    @ManyToOne
    @JoinColumn(name="poste_id")
    private Poste poste;

    public String getIdMult() {
        return idMult;
    }

    public String getUrl() {
        return url;
    }

    public Poste getPoste() {
        return poste;
    }
}
