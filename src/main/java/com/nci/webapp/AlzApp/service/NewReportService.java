package com.nci.webapp.AlzApp.service;

import com.nci.webapp.AlzApp.dto.RequestNewReport;
import com.nci.webapp.AlzApp.model.Report;
import com.nci.webapp.AlzApp.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

@Service
public class NewReportService {

    @Autowired //inject
    private ReportRepository reportRepository;

    public NewReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public void NewReport (@Valid RequestNewReport request, BindingResult result){
        Report report = request.toReport();
        reportRepository.save(report);
    }
}
