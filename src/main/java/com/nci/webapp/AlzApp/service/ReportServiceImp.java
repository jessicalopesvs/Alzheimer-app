package com.nci.webapp.AlzApp.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nci.webapp.AlzApp.api.FDADrugs;
import com.nci.webapp.AlzApp.dto.RequestNewReport;
import com.nci.webapp.AlzApp.model.Report;
import com.nci.webapp.AlzApp.repository.ReportRepository;
import com.nci.webapp.AlzApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImp implements ReportService {

    @Autowired //inject
    private ReportRepository reportRepository;

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    ReportMapper map;

    //CRUD

    //CREATE
    @Override
    public void saveReport(Report report) {
        this.reportRepository.save(report);
    }

    //GET

    @Override
    public Report getReportById(long id) {
        Optional<Report> optional = reportRepository.findById(id);
        Report report = null;

        if(optional.isPresent()){
            report = optional.get();
        }else{
            throw new RuntimeException("Report not found");
        }
        return report;

    }

    //delete

    @Override
    public void deleteReportById(long id) {
        this.reportRepository.deleteById(id);
    }

    //CALLING THE FDA API;

    @Override
    public List sideEffectsApi(String drugName) {

        FDADrugs drugs;

        List<String> sideEffectsList = new ArrayList<>();
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
            drugs.toReactions().stream().forEach(d -> sideEffectsList.add(d));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sideEffectsList;
    }


    //pages

    @Override
    public Page<Report> findPage(int pageNo, int pageSize, String sortField, String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = (Pageable) PageRequest.of(pageNo - 1, pageSize, sort);
        return this.reportRepository.findAll((org.springframework.data.domain.Pageable) pageable);
    }



}
