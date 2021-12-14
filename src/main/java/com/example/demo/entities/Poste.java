package com.example.demo.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Poste {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String idPst;

    private String nomPst;
    private String descPst;
    private boolean status;
    private Date datePub;
    private int countUp;
    private int countDn;

    @ManyToOne
    @JoinColumn(name="club_id")
    private Club club;

    @OneToMany(mappedBy = "poste")
    private List<Multimedia> multimedias;

    public String getIdPst() {
        return idPst;
    }

    public String getNomPst() {
        return nomPst;
    }

    public String getDescPst() {
        return descPst;
    }

    public boolean isStatus() {
        return status;
    }

    public Date getDatePub() {
        return datePub;
    }

    public int getCountUp() {
        return countUp;
    }

    public int getCountDn() {
        return countDn;
    }

    public Club getClub() {
        return club;
    }

    public List<Multimedia> getMultimedias() {
        return multimedias;
    }
}
