package com.example.demo.entities;

import javax.persistence.*;

@Entity
public class SponsorBudget {

    @EmbeddedId
    private SponsorBudgetId id;

    @ManyToOne
    @MapsId("idSpr")
    @JoinColumn(name = "idSponsor")
    Sponsor sponsor;

    @ManyToOne
    @MapsId("idAct")
    @JoinColumn(name = "idActivite")
    Activite activite;

    private double budget;

    public SponsorBudget() {}

    public SponsorBudgetId getId() {
        return id;
    }

    public void setId(SponsorBudgetId id) {
        this.id = id;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}
