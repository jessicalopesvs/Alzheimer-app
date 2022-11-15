package com.nci.webapp.AlzApp.service;

import com.nci.webapp.AlzApp.model.Report;
import com.nci.webapp.AlzApp.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class TableService {
    @Autowired //inject
    private ReportRepository reportRepository;

    public TableService() {
        this.reportRepository = reportRepository;
    }

    public void table(ReportRepository repository, Model model) {

    }


}
