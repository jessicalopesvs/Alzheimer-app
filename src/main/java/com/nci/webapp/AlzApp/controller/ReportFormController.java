package com.nci.webapp.AlzApp.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nci.webapp.AlzApp.dto.FDADrugs;
import com.nci.webapp.AlzApp.dto.RequestNewReport;
import com.nci.webapp.AlzApp.model.MoodList;
import com.nci.webapp.AlzApp.model.Report;
import com.nci.webapp.AlzApp.repository.ReportRepository;
import com.nci.webapp.AlzApp.service.NewReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Controller
@RequestMapping("/report")
public class ReportFormController {

    @Autowired //inject
    private ReportRepository reportRepository;

    @GetMapping("/new")
    public String ReportForm(RequestNewReport request, Model model) {
        HashMap<String, Integer> moods = new HashMap<>();
        Arrays.stream(MoodList.values()).forEach(mood -> moods.put(mood.getDisplayValue(), 0));
        model.addAttribute("moods", moods);
        return "report/new-report";
    }

    @PostMapping("/new-report")
    public String NewReport(@Valid RequestNewReport request, BindingResult result, Model model) {
        //declare the service
        NewReportService service = new NewReportService(reportRepository);

        //calling the service
        service.NewReport(request, result);

        return "redirect:/table";
    }

    @GetMapping("/table")
    public String table(Model model) {

        List<Report> reports = reportRepository.findAll();
        model.addAttribute("reports", reports);

        return "report/table";
    }

}
