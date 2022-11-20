package com.nci.webapp.AlzApp.dto;


//validation class

import com.nci.webapp.AlzApp.model.Emotions;
import com.nci.webapp.AlzApp.model.MoodList;
import com.nci.webapp.AlzApp.model.Report;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class RequestNewReport implements Serializable {

    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date date;
    private String drug;
    private int dayClassification;
    private int moodSwingClass;
    private MoodList moodSwing;

    private HashMap<String, Integer> moods;

    //emotions

    private int trust, disgust, fear, sadness, anticipation, joy, surprise, anger;


    //symptoms

    private int sleepy, weak, nauseus, vomit, lackAppetite, headache, bodyache;

    public int getSleepy() {
        return sleepy;
    }

    public void setSleepy(int sleepy) {
        this.sleepy = sleepy;
    }

    public int getWeak() {
        return weak;
    }

    public void setWeak(int weak) {
        this.weak = weak;
    }

    public int getNauseus() {
        return nauseus;
    }

    public void setNauseus(int nauseus) {
        this.nauseus = nauseus;
    }

    public int getVomit() {
        return vomit;
    }

    public void setVomit(int vomit) {
        this.vomit = vomit;
    }

    public int getLackAppetite() {
        return lackAppetite;
    }

    public void setLackAppetite(int lackAppetite) {
        this.lackAppetite = lackAppetite;
    }

    public int getHeadache() {
        return headache;
    }

    public void setHeadache(int headache) {
        this.headache = headache;
    }

    public int getBodyache() {
        return bodyache;
    }

    public void setBodyache(int bodyache) {
        this.bodyache = bodyache;
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

    public int getAnger() {
        return anger;
    }

    public void setAnger(int anger) {
        this.anger = anger;
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

    public HashMap<String, Integer> getMoods() {
        return moods;
    }

    public void setMoods(HashMap<String, Integer> moods) {
        this.moods = moods;
    }

    //add to report table

    public Report toReport(){
        Report report = new Report();
        List<Emotions> emotionsList = new ArrayList<>();

        //Setting emotions


        //setting the data filled in the form
        report.setDate(date);
        report.setDayClassification(dayClassification);
        report.setDrug(drug);
        report.setMoodSwingClass(moodSwingClass);
        report.setMood(moodSwing);

        //setting emotions list

        report.setAnticipation(anticipation);
        report.setDisgust(disgust);
        report.setFear(fear);
        report.setJoy(joy);
        report.setSadness(sadness);
        report.setSurprise(surprise);
        report.setTrust(trust);
        report.setAnger(anger);

        //setting symptoms list

        report.setSleepy(sleepy);
        report.setWeak(weak);
        report.setNauseus(nauseus);
        report.setVomit(vomit);
        report.setLackAppetite(lackAppetite);
        report.setHeadache(headache);
        report.setBodyache(bodyache);

        return report;
    }


}
