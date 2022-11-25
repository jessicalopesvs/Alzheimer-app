package com.nci.webapp.AlzApp.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nci.webapp.AlzApp.api.FDADrugs;
import com.nci.webapp.AlzApp.dto.RequestNewReport;
import com.nci.webapp.AlzApp.model.Emotions;
import com.nci.webapp.AlzApp.model.Report;
import com.nci.webapp.AlzApp.model.Symptoms;
import com.nci.webapp.AlzApp.model.User;
import com.nci.webapp.AlzApp.repository.ReportRepository;
import com.nci.webapp.AlzApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class NewReportService {

    @Autowired //inject
    private ReportRepository reportRepository;

    @Autowired
    private UserRepository userRepository;

    public NewReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

//    public void NewReport (@Valid RequestNewReport request, BindingResult result){
//
//        Report report = request.toReport();
//
//
//        // TODO: Call external API
//        String drugName = report.getDrug();
//        FDADrugs drugs;
//
//        List<String> sideEffectsList = new ArrayList<>();
//        try {
//            StringBuilder response = new StringBuilder();
//            URL url = new URL("https://api.fda.gov/drug/event.json?search=\"" + drugName + "\"");
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            try (BufferedReader reader = new BufferedReader(
//                    new InputStreamReader(conn.getInputStream()))) {
//                for (String line; (line = reader.readLine()) != null; ) {
//                    response.append(line);
//                }
//            }
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//            drugs = mapper.readValue(response.toString(), FDADrugs.class);
//            drugs.toReactions().stream().forEach(d -> sideEffectsList.add(d));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(sideEffectsList.toString());
//        report.setUser(user);
//        report.setSideEffects(sideEffectsList);
//
//        reportRepository.save(report);
//    }


    public Model maps (Model model){
        //setting the Emotions list field
        HashMap<String, Integer> behaviour = new HashMap<>();
        Arrays.stream(Emotions.values()).forEach(emotions -> behaviour.put(emotions.getDisplayValue(), 0));

        System.out.println(behaviour.toString());
        model.addAttribute("behaviour", behaviour);


        //setting list of Symptoms field
        HashMap<String, Integer> symptoms = new HashMap<>();
        Arrays.stream(Symptoms.values()).forEach(symptom -> symptoms.put(symptom.getDisplayValue(), 0));

        System.out.println(symptoms.toString());

        //setting attributes
        model.addAttribute("symptoms", symptoms);
        return model;

    }


    public List<Report> getAllReports(){
        return reportRepository.findAll();
    }
}
