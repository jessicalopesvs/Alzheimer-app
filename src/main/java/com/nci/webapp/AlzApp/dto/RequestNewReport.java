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

import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
public class RequestNewReport implements Serializable {


    private long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
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


    //add to report table

    public Report toReport(){
        Report report = new Report();

        if (id != 0){
            report.setId(id);
        }

        //setting the data filled in the form
        report.setDate(date);
        report.setDayClassification(dayClassification);
        report.setDrug(drug);
        report.setMoodSwingClass(moodSwingClass);

        //Setting lists
        report.setSideEffects(sideEffects);
        report.setBehaviour(behaviour);
        report.setSymptom(symptom);

        return report;
    }


}
