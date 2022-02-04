package com.example.demo.entities;

public class RequestUpdateClub {

    private Club clubRequest;
    private Referent referentRequest;
    private Membre presidentRequest;
    private Membre vicePresidentRequest;
    private Membre tresorierRequest;
    private Membre secretaireRequest;
    private String nameClub;

    public RequestUpdateClub(Club clubRequest, Referent referentRequest, Membre presidentRequest, Membre vicePresidentRequest, Membre tresorierRequest, Membre secretaireRequest, String idClub) {
        this.clubRequest = clubRequest;
        this.referentRequest = referentRequest;
        this.presidentRequest = presidentRequest;
        this.vicePresidentRequest = vicePresidentRequest;
        this.tresorierRequest = tresorierRequest;
        this.secretaireRequest = secretaireRequest;
        this.nameClub = idClub;
    }

    public RequestUpdateClub() {
    }

    public Club getClubRequest() {
        return clubRequest;
    }

    public void setClubRequest(Club clubRequest) {
        this.clubRequest = clubRequest;
    }

    public Referent getReferentRequest() {
        return referentRequest;
    }

    public void setReferentRequest(Referent referentRequest) {
        this.referentRequest = referentRequest;
    }

    public Membre getPresidentRequest() {
        return presidentRequest;
    }

    public void setPresidentRequest(Membre presidentRequest) {
        this.presidentRequest = presidentRequest;
    }

    public Membre getVicePresidentRequest() {
        return vicePresidentRequest;
    }

    public void setVicePresidentRequest(Membre vicePresidentRequest) {
        this.vicePresidentRequest = vicePresidentRequest;
    }

    public Membre getTresorierRequest() {
        return tresorierRequest;
    }

    public void setTresorierRequest(Membre tresorierRequest) {
        this.tresorierRequest = tresorierRequest;
    }

    public Membre getSecretaireRequest() {
        return secretaireRequest;
    }

    public void setSecretaireRequest(Membre secretaireRequest) {
        this.secretaireRequest = secretaireRequest;
    }

    public String getNameClub() {
        return nameClub;
    }

    public void setNameClub(String nameClub) {
        this.nameClub = nameClub;
    }
}
