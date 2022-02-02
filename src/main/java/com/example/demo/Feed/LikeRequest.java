package com.example.demo.Feed;

public class LikeRequest {

    private Integer idPost;
    private long idUser;

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public LikeRequest() {
    }

    public LikeRequest(Integer idPost, long idIser) {
        this.idPost = idPost;
        this.idUser = idIser;
    }
}
