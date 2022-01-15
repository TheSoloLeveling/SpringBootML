package com.example.demo.entities;

import com.example.demo.repositories.FonctionnaliteRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.context.properties.bind.Name;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "club")
public class Club {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String idClub;


    private String nomClub;
    private String descClub;
    private Date dateCre;
    private boolean status;
    private String logo;
    private String coverImg;

    @OneToOne
    @JoinColumn(name = "tresorerie_id")
    private Tresorerie tresorerie;

    @JsonIgnore
    @OneToMany(mappedBy = "club")
    private List<Activite> activites;

    @ManyToOne
    @JoinColumn(name="affiliation_id")
    private Affiliation affiliation;

    @JsonIgnore
    @OneToMany(mappedBy = "club")
    private List<Reunion> reunions;

    @JsonIgnore
    @OneToMany(mappedBy = "club")
    private List<Poste> postes;

    @ManyToOne
    @JoinColumn(name="categorie_id")
    private Categorie categorie;

    @ManyToOne
    @JoinColumn(name="referent_id")
    private Referent referent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "club")
    private LinkedList<Membre> membres;

    public Club() {

    }


    public Club(String idClub, String nomClub, String descClub, Date dateCre, boolean status, String logo, String coverImg) {
        this.idClub = idClub;
        this.nomClub = nomClub;
        this.descClub = descClub;
        this.dateCre = dateCre;
        this.status = status;
        this.logo = logo;
        this.coverImg = coverImg;
    }

    public Membre findMember(EFonction e){

        Fonctionnalite f = new Fonctionnalite(e);

        for(int i = 0; i < membres.size(); i++){
            if (membres.get(i).getFonctionnalites().contains(f)){
                System.out.println("Member with function : " + e.name() +" is found");
                return membres.get(i);
            }
        }
        System.out.println(" Error: Member with function : " + e.name() + " not found");
        return null;
    }

    public void setIdClub(String idClub) {
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

    public void setTresorerie(Tresorerie tresorerie) {
        this.tresorerie = tresorerie;
    }

    public void setActivites(List<Activite> activites) {
        this.activites = activites;
    }

    public void setAffiliation(Affiliation affiliation) {
        this.affiliation = affiliation;
    }

    public void setReunions(List<Reunion> reunions) {
        this.reunions = reunions;
    }

    public void setPostes(List<Poste> postes) {
        this.postes = postes;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public void setReferent(Referent referent) {
        this.referent = referent;
    }

    public void setMembres(LinkedList<Membre> membres) {
        this.membres = membres;
    }

    public String getIdClub() {
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

    public String getCoverImg() {
        return coverImg;
    }

    public Tresorerie getTresorerie() {
        return tresorerie;
    }

    public List<Activite> getActivites() { return activites; }

    public Affiliation getAffiliation() {
        return affiliation;
    }

    public List<Reunion> getReunions() {
        return reunions;
    }

    public List<Poste> getPostes() {
        return postes;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public Referent getReferent() {
        return referent;
    }

    public LinkedList<Membre> getMembres() {
        return membres;
    }
}
