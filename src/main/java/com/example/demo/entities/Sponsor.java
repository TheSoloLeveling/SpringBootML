package com.example.demo.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Sponsor {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String idSpr;

    private String nomSpr;
    private String typeSpr;
    private String logoSpr;

    @OneToMany(mappedBy = "sponsor")
    private List<SponsorBudget> sponsorBudgets;

    public String getIdSpr() {
        return idSpr;
    }

    public String getNomSpr() {
        return nomSpr;
    }

    public String getTypeSpr() {
        return typeSpr;
    }

    public String getLogoSpr() {
        return logoSpr;
    }

    public List<SponsorBudget> getSponsorBudgets() {
        return sponsorBudgets;
    }
}
