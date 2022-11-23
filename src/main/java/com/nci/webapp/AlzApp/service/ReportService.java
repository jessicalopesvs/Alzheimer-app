package com.nci.webapp.AlzApp.service;

import com.nci.webapp.AlzApp.model.Report;
import com.nci.webapp.AlzApp.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired //inject
    private ReportRepository repository;

    public Model moodChart(Model model) {

        List<Date> date = repository.findAll().stream().map(x -> x.getDate()).collect(Collectors.toList());

        String fristDate = date.get(0).toString();

        List<Report> reports = repository.findAll();

        Map<Date, Integer> anger = new LinkedHashMap<>();
        for (Report r : reports) {
            anger.put(r.getDate(), r.getAnger());
        }

        Map<Date, Integer> trust = new LinkedHashMap<>();
        for (Report i : reports) {
            trust.put(i.getDate(), i.getTrust());
        }

        Map<Date, Integer> surprise = new LinkedHashMap<>();
        for (Report r : reports) {
            surprise.put(r.getDate(), r.getSurprise());
        }

        Map<Date, Integer> anticipation = new LinkedHashMap<>();
        for (Report r : reports) {
            anticipation.put(r.getDate(), r.getAnticipation());
        }

        Map<Date, Integer> disgust = new LinkedHashMap<>();
        for (Report r : reports) {
            disgust.put(r.getDate(), r.getDisgust());
        }

        Map<Date, Integer> fear = new LinkedHashMap<>();
        for (Report r : reports) {
            fear.put(r.getDate(), r.getFear());
        }
        Map<Date, Integer> sadness = new LinkedHashMap<>();
        for (Report r : reports) {
            sadness.put(r.getDate(), r.getSadness());
        }
        Map<Date, Integer> joy = new LinkedHashMap<>();
        for (Report r : reports) {
            joy.put(r.getDate(), r.getJoy());
        }


        //setting attributes
        model.addAttribute("date", fristDate);
        model.addAttribute("anger", anger);
        model.addAttribute("trust", trust);
        model.addAttribute("surprise", surprise);
        model.addAttribute("anticipation", anticipation);
        model.addAttribute("disgust", disgust);
        model.addAttribute("fear", fear);
        model.addAttribute("sadness", sadness);
        model.addAttribute("joy", joy);

        return model;
    }



}
