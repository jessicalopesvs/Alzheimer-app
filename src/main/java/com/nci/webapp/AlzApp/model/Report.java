package com.nci.webapp.AlzApp.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class Report implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date date;
    private String drug;
    private int dayClassification;
    private int moodSwingClass;
    private MoodList moodSwing;

    public int getMoodSwingClass() {
        return moodSwingClass;
    }

    public void setMoodSwingClass(int moodSwingClass) {
        this.moodSwingClass = moodSwingClass;
    }

    public MoodList getMoodSwing() {
        return moodSwing;
    }

    public void setMoodSwing(MoodList moodSwing) {
        this.moodSwing = moodSwing;
    }

    private MoodList mood;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public int getDayClassification() {
        return dayClassification;
    }

    public void setDayClassification(int dayClassification) {
        this.dayClassification = dayClassification;
    }

    public MoodList getMood() {
        return mood;
    }

    public void setMood(MoodList mood) {
        this.mood = mood;
    }
}
