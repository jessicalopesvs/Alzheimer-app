package com.nci.webapp.AlzApp.dto;

import javax.validation.constraints.NotBlank;


//validation class

import com.nci.webapp.AlzApp.model.MoodList;
import com.nci.webapp.AlzApp.model.Report;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class RequestNewReport implements Serializable {

    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date date;
    private String drug;
    private int dayClassification;
    private int moodSwingClass;
    private MoodList moodSwing;

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

//add to report table

    public Report toReport(){
        Report report = new Report();
        //setting the data filled in the form
        report.setDate(date);
        report.setDayClassification(dayClassification);
        report.setDrug(drug);
        report.setMoodSwingClass(moodSwingClass);
        report.setMood(moodSwing);


        return report;
    }
}
