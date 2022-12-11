package com.nci.webapp.AlzApp.service;

import com.nci.webapp.AlzApp.model.Report;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;

public interface ReportService {

    //CRUD CLASSES
    Report getReportById (long id);
    void saveReport (Report report);
    void deleteReportById (long id);
    //observation: update is in the controller

    //API CLASSES
    public List sideEffectsApi(String drug);

    public HashMap<String, Integer> behaviourList ();
    public HashMap<String, Integer> symptomsList ();

}
