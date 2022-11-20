package com.nci.webapp.AlzApp.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nci.webapp.AlzApp.dto.FDADrugs;
import com.nci.webapp.AlzApp.dto.RequestNewReport;
import com.nci.webapp.AlzApp.model.Emotions;
import com.nci.webapp.AlzApp.model.Report;
import com.nci.webapp.AlzApp.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class NewReportService {

    @Autowired //inject
    private ReportRepository reportRepository;

    public NewReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public void NewReport (@Valid RequestNewReport request, BindingResult result){
        Report report = request.toReport();

        // TODO: Call external API
        String drugName = report.getDrug();
        FDADrugs drugs;
        try {
            StringBuilder response = new StringBuilder();
            URL url = new URL("https://api.fda.gov/drug/event.json?search=\"" + drugName + "\"");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()))) {
                for (String line; (line = reader.readLine()) != null; ) {
                    response.append(line);
                }
            }
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            drugs = mapper.readValue(response.toString(), FDADrugs.class);
            drugs.toReactions().stream().forEach(d -> System.out.println(d));
        } catch (IOException e) {
            e.printStackTrace();
        }


        reportRepository.save(report);
    }

    public void emotions(){
        Report report = new Report();



    }
}
