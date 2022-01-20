package com.example.demo.entities;

import org.springframework.web.multipart.MultipartFile;

public class RequestCreateClub {

    private Club clubRequest;
    private Referent referentRequest;
    private Membre presidentRequest;
    private Membre vicePresidentRequest;
    private Membre tresorierRequest;
    private Membre secretaireRequest;


    public void setClubRequest(Club clubRequest) {
        this.clubRequest = clubRequest;
    }

    public void setReferentRequest(Referent referentRequest) {
        this.referentRequest = referentRequest;
    }

    public void setPresidentRequest(Membre presidentRequest) {
        this.presidentRequest = presidentRequest;
    }

    public void setVicePresidentRequest(Membre vicePresidentRequest) {
        this.vicePresidentRequest = vicePresidentRequest;
    }

    public void setTresorierRequest(Membre tresorierRequest) {
        this.tresorierRequest = tresorierRequest;
    }

    public void setSecretaireRequest(Membre secretaireRequest) {
        this.secretaireRequest = secretaireRequest;
    }



    public Club getClubRequest() {
        return clubRequest;
    }

    public Referent getReferentRequest() {
        return referentRequest;
    }

    public Membre getPresidentRequest() {
        return presidentRequest;
    }

    public Membre getVicePresidentRequest() {
        return vicePresidentRequest;
    }

    public Membre getTresorierRequest() {
        return tresorierRequest;
    }

    public Membre getSecretaireRequest() {
        return secretaireRequest;
    }

    @Override
    public String toString() {
        return "RequestCreateClub{" +
                "clubRequest=" + clubRequest +
                ", referentRequest=" + referentRequest +
                ", presidentRequest=" + presidentRequest +
                ", vicePresidentRequest=" + vicePresidentRequest +
                ", tresorierRequest=" + tresorierRequest +
                ", secretaireRequest=" + secretaireRequest +
                '}';
    }

    public RequestCreateClub(Club clubRequest, Referent referentRequest, Membre presidentRequest, Membre vicePresidentRequest, Membre tresorierRequest, Membre secretaireRequest) {
        this.clubRequest = clubRequest;
        this.referentRequest = referentRequest;
        this.presidentRequest = presidentRequest;
        this.vicePresidentRequest = vicePresidentRequest;
        this.tresorierRequest = tresorierRequest;
        this.secretaireRequest = secretaireRequest;

    }

    public RequestCreateClub() {
    }
}
