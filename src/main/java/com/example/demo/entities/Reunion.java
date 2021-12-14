package com.example.demo.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reunion {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String idReu;

    private Date dateReu;
    private int nrbMembr;
    private String pv;

    @ManyToOne
    @JoinColumn(name="club_id")
    private Club club;

    public String getIdReu() {
        return idReu;
    }

    public Date getDateReu() {
        return dateReu;
    }

    public int getNrbMembr() {
        return nrbMembr;
    }

    public String getPv() {
        return pv;
    }

    public Club getClub() {
        return club;
    }
}
