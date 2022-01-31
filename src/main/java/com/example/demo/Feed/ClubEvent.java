package com.example.demo.Feed;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Optional;

@Entity
@Table(name = "ClubEvents")
public class ClubEvent {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="eventID", unique = true, nullable = false)
    private Integer eventID;

    @Column(name = "post_id")
    private Integer idPost;

    private Timestamp dateTime;

    private String name;
    private String eventDesc;


    private Timestamp startDate;
    private Timestamp endDate;

    private int maxP;
    private float budgetE;
    private int building;
    private int amphi;

    private int actualP;
    private String materiels;

    public Optional<String> getMateriels() {
        return Optional.ofNullable(materiels);
    }

    public ClubEvent(Integer idPost, Timestamp dateTime, String name, String eventDesc, Timestamp startDate, Timestamp endDate, int maxP, float budgetE, int building, int amphi, int actualP, String materiels) {
        this.idPost = idPost;
        this.dateTime = dateTime;
        this.name = name;
        this.eventDesc = eventDesc;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxP = maxP;
        this.budgetE = budgetE;
        this.building = building;
        this.amphi = amphi;
        this.actualP = actualP;
        this.materiels = materiels;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public void setMateriels(String materiels) {
        this.materiels = materiels;
    }

    public ClubEvent() {
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public ClubEvent(Integer idPost, String name, String eventDesc, Timestamp startDate, Timestamp endDate, int maxP, float budgetE, int building, int amphi, int actualP) {
        this.idPost = idPost;
        this.name = name;
        this.eventDesc = eventDesc;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxP = maxP;
        this.budgetE = budgetE;
        this.building = building;
        this.amphi = amphi;
        this.actualP = actualP;
    }

    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public int getMaxP() {
        return maxP;
    }

    public void setMaxP(int maxP) {
        this.maxP = maxP;
    }

    public float getBudgetE() {
        return budgetE;
    }

    public void setBudgetE(float budgetE) {
        this.budgetE = budgetE;
    }

    public int getBuilding() {
        return building;
    }

    public void setBuilding(int building) {
        this.building = building;
    }

    public int getAmphi() {
        return amphi;
    }

    public void setAmphi(int amphi) {
        this.amphi = amphi;
    }

    public int getActualP() {
        return actualP;
    }

    public void setActualP(int actualP) {
        this.actualP = actualP;
    }
}
