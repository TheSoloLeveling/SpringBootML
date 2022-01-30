package com.example.demo.Feed;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Posts")
public class Post {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="postID", unique = true, nullable = false)
    private Integer postID;

    @Column(name = "club_id")
    private Integer idClub;

    private String clubName;

    private Long userID;
    private String userName;


    private String description;
    private String postImgURL;
    private String postVideo;

    private int likes;
    private Timestamp dateTime;

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public Optional<String> getPostVideo() {
        return Optional.ofNullable(postVideo);
    }

    public void setPostVideo(String postVideo) {
        this.postVideo = postVideo;
    }

    public Integer getIdClub() {
        return idClub;
    }

    public void setIdClub(Integer idClub) {
        this.idClub = idClub;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name="post_id", referencedColumnName = "postID")
    private Set<Comment> comments;

    public Post() {
    }

    public Post(String userName, String description, String postImgURL, int likes, Timestamp dateTime, Set<Comment> comments) {
        this.userName = userName;
        this.description = description;
        this.postImgURL = postImgURL;
        this.likes = likes;
        this.dateTime = dateTime;
        this.comments = comments;
    }

    public Integer getPostID() {
        return postID;
    }

    public void setPostID(Integer postID) {
        this.postID = postID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Optional<String> getPostImgURL() {
        return Optional.ofNullable(postImgURL);
    }

    public void setPostImgURL(String postImgURL) {
        this.postImgURL = postImgURL;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
