package com.example.demo.Feed;

import java.sql.Timestamp;

public class EventRequest {

    private String name;
    private String eventDesc;
    private Timestamp startDate;
    private Timestamp endDate;
    private int maxP;
    private float budgetE;
    private int building;
    private int amphi;
    private int actuaP;
    private Integer postID;

    public EventRequest(String name, String eventDesc, Timestamp startDate, Timestamp endDate, int maxP, float budgetE, int building, int amphi, int actuaP, Integer postID) {
        this.name = name;
        this.eventDesc = eventDesc;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxP = maxP;
        this.budgetE = budgetE;
        this.building = building;
        this.amphi = amphi;
        this.actuaP = actuaP;
        this.postID = postID;
    }

    public EventRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
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

    public int getActuaP() {
        return actuaP;
    }

    public void setActuaP(int actuaP) {
        this.actuaP = actuaP;
    }

    public Integer getPostID() {
        return postID;
    }

    public void setPostID(Integer postID) {
        this.postID = postID;
    }
}
