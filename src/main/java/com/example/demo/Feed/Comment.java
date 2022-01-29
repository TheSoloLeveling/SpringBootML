package com.example.demo.Feed;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="commentID", unique = true, nullable = false)
    private Integer commentID;

    private String userID;

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    @Column(name = "post_id")
    private Integer idPost;

    private String userImage;
    private String userName;
    private String text;

    @Column(name = "comment_id")
    private Integer idSubComment;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name="comment_id", referencedColumnName = "commentID")
    private Set<Comment> comments;

    public Comment(String userImage, String userName, String text, Set<Comment> comments, Timestamp timestamp) {
        this.userImage = userImage;
        this.userName = userName;
        this.text = text;
        this.comments = comments;
        this.timestamp = timestamp;
    }

    public Comment() {
    }

    private Timestamp timestamp;

    public Integer getCommentID() {
        return commentID;
    }

    public void setCommentID(Integer commentID) {
        this.commentID = commentID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Integer getIdSubComment() {
        return idSubComment;
    }

    public void setIdSubComment(Integer idSubComment) {
        this.idSubComment = idSubComment;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
