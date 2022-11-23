package com.nci.webapp.AlzApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Report implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date date;
    private String drug;
    private int dayClassification;
    private int moodSwingClass;
    @Column(name="arguments")
    @ElementCollection(targetClass=String.class)
    private List<String> sideEffects = new ArrayList<>();

    @Column(name="emotionRating")
    @MapKeyColumn(name="emotion")
    @ElementCollection
    private Map<String,Integer> behaviour = new HashMap<>();


    @MapKeyColumn(name="symptoms")
    @Column(name="symptomRating")
    @ElementCollection
    private Map<String,Integer> symptom= new HashMap<>();




    @JsonIgnore
    @ManyToOne (fetch = FetchType.EAGER)
    private User user;


//    List<Behavior> behaviors;

//    @entity
//    class Behavior
//    String name;
//    int classification;
//    enum type; // Emotion, Symptom

//    getBehaviors -> filter by type == Emotion

    //emotions

    private int trust, disgust, fear, sadness, anticipation, joy, surprise, anger;


    //symptoms

    private int sleepy, weak, nauseus, vomit, lackAppetite, headache, bodyache, confusionalState;

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

    public int getMoodSwingClass() {
        return moodSwingClass;
    }

    public void setMoodSwingClass(int moodSwingClass) {
        this.moodSwingClass = moodSwingClass;
    }

    public List<String> getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(List<String> sideEffects) {
        this.sideEffects = sideEffects;
    }

    public Map<String, Integer> getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(Map<String, Integer> behaviour) {
        this.behaviour = behaviour;
    }

    public Map<String, Integer> getSymptom() {
        return symptom;
    }

    public void setSymptom(Map<String, Integer> symptom) {
        this.symptom = symptom;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public int getConfusionalState() {
        return confusionalState;
    }

    public void setConfusionalState(int confusionalState) {
        this.confusionalState = confusionalState;
    }
}
