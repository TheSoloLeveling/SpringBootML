package com.example.demo.entities;

import javax.persistence.*;

@Entity
public class ClubFollowed {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "clubToFolloId", unique = true, nullable = false)
    private Integer id;

    private String name;

    public ClubFollowed() {
    }

    public ClubFollowed(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
