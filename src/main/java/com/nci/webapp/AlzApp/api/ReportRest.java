package com.nci.webapp.AlzApp.api;

import com.nci.webapp.AlzApp.model.Report;
import com.nci.webapp.AlzApp.model.User;
import com.nci.webapp.AlzApp.repository.ReportRepository;
import com.nci.webapp.AlzApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/reports")
public class ReportRest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReportRepository reportRepository;

    @GetMapping("user-report.json")
    public List<User> getReports(Principal principal){

        String username = "user";
        Sort sort = Sort.by("date").ascending();

        return userRepository.findAll();
    }

    @GetMapping ("report.json")
    public List<Report> getReport(Principal principal){

        List<Report> reports = reportRepository.findAllByUser(principal.getName());

        Sort sort = Sort.by("date").ascending();

        return reportRepository.findAll();
    }
}
