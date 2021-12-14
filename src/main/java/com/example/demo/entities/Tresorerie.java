package com.example.demo.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Tresorerie {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String idTres;

    private Double budget;
    private Date annAca;

    @OneToOne(mappedBy = "tresorerie")
    private Club club;

    public String getIdTres() {
        return idTres;
    }

    public Double getBudget() {
        return budget;
    }

    public Date getAnnAca() {
        return annAca;
    }

    public Club getClub() {
        return club;
    }
}
