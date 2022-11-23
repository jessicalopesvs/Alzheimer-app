package com.nci.webapp.AlzApp.controller;

import com.nci.webapp.AlzApp.dto.RequestNewReport;
import com.nci.webapp.AlzApp.model.Report;
import com.nci.webapp.AlzApp.repository.ReportRepository;
import com.nci.webapp.AlzApp.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/report/dashboard")
public class ReportController {

    @Autowired //inject
    private ReportRepository reportRepository;

    @Autowired
    private ReportService service;

//
//    @GetMapping
//    public String dashboard(RequestNewReport request, Model model) {
//
//
//        return "/report/dashboard";
//    }

    @GetMapping
    public String moodChart(Model model) {

        service.moodChart(model);

        return "/report/dashboard";
    }
}
