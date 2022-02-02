package com.example.demo.Feed;

import javax.persistence.*;

@Entity
public class PostLiked {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "clubToFolloId", unique = true, nullable = false)
    private Integer idPostLiked;

    private String nameClub;
    private Integer idPost;

    public PostLiked(String nameClub, Integer idPost) {
        this.nameClub = nameClub;
        this.idPost = idPost;
    }

    public PostLiked() {
    }

    public Integer getIdPostLiked() {
        return idPostLiked;
    }

    public void setIdPostLiked(Integer idPostLiked) {
        this.idPostLiked = idPostLiked;
    }

    public String getNameClub() {
        return nameClub;
    }

    public void setNameClub(String nameClub) {
        this.nameClub = nameClub;
    }

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }
}
