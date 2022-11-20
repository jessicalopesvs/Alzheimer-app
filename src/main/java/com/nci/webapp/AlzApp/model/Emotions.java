package com.nci.webapp.AlzApp.model;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Emotions {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date date;

    //Emotions list
    private int trust;
    private int disgust;
    private int fear;
    private int sadness;
    private int anticipation;
    private int joy;
    private int surprise;


    @ManyToOne(fetch = FetchType.EAGER)
    private Report reports;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTrust() {
        return trust;
    }

    public void setTrust(int trust) {
        this.trust = trust;
    }

    public int getDisgust() {
        return disgust;
    }

    public void setDisgust(int disgust) {
        this.disgust = disgust;
    }

    public int getFear() {
        return fear;
    }

    public void setFear(int fear) {
        this.fear = fear;
    }

    public int getSadness() {
        return sadness;
    }

    public void setSadness(int sadness) {
        this.sadness = sadness;
    }

    public int getAnticipation() {
        return anticipation;
    }

    public void setAnticipation(int anticipation) {
        this.anticipation = anticipation;
    }

    public int getJoy() {
        return joy;
    }

    public void setJoy(int joy) {
        this.joy = joy;
    }

    public int getSurprise() {
        return surprise;
    }

    public void setSurprise(int surprise) {
        this.surprise = surprise;
    }

    public Report getReports() {
        return reports;
    }

    public void setReports(Report reports) {
        this.reports = reports;
    }
}
