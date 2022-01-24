package com.example.demo.entities;

import javax.persistence.*;

@Entity
public class ClubFollowed {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "clubToFolloId", unique = true, nullable = false)
    private Integer idClubFollowed;

    private String name;
    private  Integer idClub;

    public ClubFollowed(String name, Integer idClub) {
        this.name = name;
        this.idClub = idClub;
    }

    public Integer getIdClub() {
        return idClub;
    }

    public void setIdClub(Integer idClub) {
        this.idClub = idClub;
    }

    public ClubFollowed() {
    }

    public ClubFollowed(Integer id, String name) {
        this.idClubFollowed = id;
        this.name = name;
    }

    public ClubFollowed(String name) {
        this.name = name;
    }

    public void setIdClubFollowed(Integer id) {
        this.idClubFollowed = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdClubFollowed() {
        return idClubFollowed;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ClubFollowed{" +
                "id=" + idClubFollowed +
                ", name='" + name + '\'' +
                '}';
    }
}
