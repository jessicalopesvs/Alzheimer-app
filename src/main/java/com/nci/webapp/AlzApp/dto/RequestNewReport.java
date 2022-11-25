package com.nci.webapp.AlzApp.dto;


//validation class

import com.nci.webapp.AlzApp.model.Emotions;
import com.nci.webapp.AlzApp.model.Report;
import com.nci.webapp.AlzApp.model.Symptoms;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
public class RequestNewReport implements Serializable {

    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date date;
    private String drug;
    private int dayClassification;
    private int moodSwingClass;
    private Emotions moodSwing;

    //Lists


    private Map<String,Integer> behaviour = new HashMap<>();
    private List<String> sideEffects = new ArrayList<>();
    private Map<String,Integer> symptom= new HashMap<>();

    public RequestNewReport() {
        symptom = new HashMap<>();
        Arrays.stream(Symptoms.values()).forEach(s -> symptom.put(s.getDisplayValue(), 0));

    }


    //emotions

//    private int trust, disgust, fear, sadness, anticipation, joy, surprise, anger;


    //symptoms

//    private int sleepy, weak, nauseus, vomit, lackAppetite, headache, bodyache, confusionalState;



    //GETTERS AND SETTERS

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

    public Emotions getMoodSwing() {
        return moodSwing;
    }

    public void setMoodSwing(Emotions moodSwing) {
        this.moodSwing = moodSwing;
    }

    public Map<String, Integer> getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(Map<String, Integer> behaviour) {
        this.behaviour = behaviour;
    }

    public List<String> getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(List<String> sideEffects) {
        this.sideEffects = sideEffects;
    }

    public Map<String, Integer> getSymptom() {
        return symptom;
    }

    public void setSymptom(Map<String, Integer> symptom) {
        this.symptom = symptom;
    }


    //add to report table

    public Report toReport(){
        Report report = new Report();

        //Setting emotions


        //setting the data filled in the form
        report.setDate(date);
        report.setDayClassification(dayClassification);
        report.setDrug(drug);
        report.setMoodSwingClass(moodSwingClass);

        //Setting lists
        report.setSideEffects(sideEffects);
        report.setBehaviour(behaviour);
        report.setSymptom(symptom);

        //setting emotions list

//        report.setAnticipation(anticipation);
//        report.setDisgust(disgust);
//        report.setFear(fear);
//        report.setJoy(joy);
//        report.setSadness(sadness);
//        report.setSurprise(surprise);
//        report.setTrust(trust);
//        report.setAnger(anger);
//
//        //setting symptoms list
//
//        report.setSleepy(sleepy);
//        report.setWeak(weak);
//        report.setNauseus(nauseus);
//        report.setVomit(vomit);
//        report.setLackAppetite(lackAppetite);
//        report.setHeadache(headache);
//        report.setBodyache(bodyache);
//        report.setConfusionalState(confusionalState);

        return report;
    }


}
