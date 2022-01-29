package com.example.demo.Feed;

public class PostRequest {

    private String desc;
    private String nameClub;
    private String userName;
    private Long idUser;

    public PostRequest() {
    }

    public PostRequest(String desc, String nameClub, String userName, Long idUser) {
        this.desc = desc;
        this.nameClub = nameClub;
        this.userName = userName;
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNameClub() {
        return nameClub;
    }

    public void setNameClub(String nameClub) {
        this.nameClub = nameClub;
    }
}
