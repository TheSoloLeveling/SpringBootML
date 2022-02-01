package com.example.demo.Feed;

public class CommentRequest {

    private Integer idClub;
    private Integer idPost;
    private Long idUser;
    private String text;

    public CommentRequest(Integer idClub, Integer idPost, Long idUser, String text) {
        this.idClub = idClub;
        this.idPost = idPost;
        this.idUser = idUser;
        this.text = text;
    }

    public CommentRequest() {
    }

    public Integer getIdClub() {
        return idClub;
    }

    public void setIdClub(Integer idClub) {
        this.idClub = idClub;
    }

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
