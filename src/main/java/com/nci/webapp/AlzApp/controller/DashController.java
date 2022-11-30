package com.nci.webapp.AlzApp.controller;

import com.nci.webapp.AlzApp.repository.ReportRepository;
import com.nci.webapp.AlzApp.service.DashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.criteria.CriteriaBuilder;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/report/dashboard")
public class DashController {

    @Autowired //inject
    private ReportRepository reportRepository;

    @Autowired
    private DashService service;


    @GetMapping
    public String dashData(Model model, Principal principal) {


        return "/report/dashboard";
    }
}
