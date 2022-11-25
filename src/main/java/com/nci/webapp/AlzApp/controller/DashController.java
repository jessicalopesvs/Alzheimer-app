package com.nci.webapp.AlzApp.controller;

import com.nci.webapp.AlzApp.repository.ReportRepository;
import com.nci.webapp.AlzApp.service.DashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/report/dashboard")
public class DashController {

    @Autowired //inject
    private ReportRepository reportRepository;

    @Autowired
    private DashService service;


    @GetMapping
    public String moodChart(Model model, Principal principal) {

        service.moodChart(model, principal);

        return "/report/dashboard";
    }
}
