package com.example.demo.entities;

public class FollowRequest {

    private String nomClub;
    private Long idUser;

    @Override
    public String toString() {
        return "FollowRequest{" +
                "nomClub='" + nomClub + '\'' +
                ", idUser='" + idUser + '\'' +
                '}';
    }

    public FollowRequest() {
    }

    public FollowRequest(String nomClub, Long idUser) {
        this.nomClub = nomClub;
        this.idUser = idUser;
    }

    public void setNomClub(String nomClub) {
        this.nomClub = nomClub;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getNomClub() {
        return nomClub;
    }

    public Long getIdUser() {
        return idUser;
    }
}
