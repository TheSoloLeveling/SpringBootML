package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SponsorBudgetId implements Serializable {
    @Column(name = "idSponsor")
    String idSpr;

    @Column(name = "idActivite")
    String idAct;

    public SponsorBudgetId(String idSpr, String idAct) {
        this.idSpr = idSpr;
        this.idAct = idAct;
    }

    public SponsorBudgetId() {}

    public String getIdSpr() {
        return idSpr;
    }

    public void setIdSpr(String idSpr) {
        this.idSpr = idSpr;
    }

    public String getIdAct() {
        return idAct;
    }

    public void setIdAct(String idAct) {
        this.idAct = idAct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SponsorBudgetId that = (SponsorBudgetId) o;
        return Objects.equals(idSpr, that.idSpr) && Objects.equals(idAct, that.idAct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSpr, idAct);
    }
}
