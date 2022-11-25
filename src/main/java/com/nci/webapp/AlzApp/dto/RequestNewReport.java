package com.nci.webapp.AlzApp.dto;


//validation class

import com.nci.webapp.AlzApp.model.Emotions;
import com.nci.webapp.AlzApp.model.Report;
import com.nci.webapp.AlzApp.model.Symptoms;
import com.nci.webapp.AlzApp.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
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

    private int trust, disgust, fear, sadness, anticipation, joy, surprise, anger;


    //symptoms

    private int sleepy, weak, nauseus, vomit, lackAppetite, headache, bodyache, confusionalState;


    //add to report table

    public Report toReport(){
        Report report = new Report();


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
        report.setConfusionalState(confusionalState);

        return report;
    }


}
