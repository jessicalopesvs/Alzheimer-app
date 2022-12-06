package com.nci.webapp.AlzApp.service;

import com.nci.webapp.AlzApp.model.Report;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReportService {

    Report getReportById (long id);
    void saveReport (Report report);
    void deleteReportById (long id);
    Page<Report> findPage(int pageNo, int pageSize, String sortField, String sortDirection);
    public List sideEffectsApi(String drug);


}
