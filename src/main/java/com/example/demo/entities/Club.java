package com.example.demo.entities;

import com.example.demo.Feed.Post;
import com.example.demo.repositories.FonctionnaliteRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "club")
public class Club {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="idClub", unique = true, nullable = false)
    private Integer idClub;

    private String idS3;

    private String nomClub;
    private String descClub;
    private Date dateCre;
    private boolean status;
    private String logo;
    private String coverImg;
    private String email;

    private int nbrFollowers;

    public void setNbrFollowers(int nbrFollowers) {
        this.nbrFollowers = nbrFollowers;
    }

    public int getNbrFollowers() {
        return nbrFollowers;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="referent_id")
    private Referent referent;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="club_id", referencedColumnName = "idClub")
    private Set<Membre> membres;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name="club_id", referencedColumnName = "idClub")
    private Set<Post> posts;

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Club() {

    }

    public Club(String nomClub, String descClub) {
        this.nomClub = nomClub;
        this.descClub = descClub;
    }


    public void setIdClub(Integer idClub) {
        this.idClub = idClub;
    }

    public void setNomClub(String nomClub) {
        this.nomClub = nomClub;
    }

    public void setDescClub(String descClub) {
        this.descClub = descClub;
    }

    public void setDateCre(Date dateCre) {
        this.dateCre = dateCre;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }


    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public void setReferent(Referent referent) {
        this.referent = referent;
    }

    public void setMembres(Set<Membre> membres) {
        this.membres = membres;
    }

    public void setIdS3(String idS3) {
        this.idS3 = idS3;
    }

    public String getIdS3() {
        return idS3;
    }

    public Integer getIdClub() {
        return idClub;
    }

    public String getNomClub() {
        return nomClub;
    }

    public String getDescClub() {
        return descClub;
    }

    public Date getDateCre() {
        return dateCre;
    }

    public boolean isStatus() {
        return status;
    }

    public Optional<String> getLogo() {
        return Optional.ofNullable(logo);
    }

    public Optional<String> getCoverImg() {
        return Optional.ofNullable(coverImg);
    }



    public Referent getReferent() {
        return referent;
    }

    public Set<Membre> getMembres() {
        return membres;
    }

    @Override
    public String toString() {
        return "Club{" +
                "idClub='" + idClub + '\'' +
                ", idS3='" + idS3 + '\'' +
                ", nomClub='" + nomClub + '\'' +
                ", descClub='" + descClub + '\'' +
                ", dateCre=" + dateCre +
                ", status=" + status +
                ", logo='" + logo + '\'' +
                ", coverImg='" + coverImg + '\'' +
                ", referent=" + referent +
                ", membres=" + membres +
                '}';
    }
}
