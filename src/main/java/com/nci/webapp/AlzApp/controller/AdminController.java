package com.nci.webapp.AlzApp.controller;

import com.nci.webapp.AlzApp.model.Report;
import com.nci.webapp.AlzApp.model.User;
import com.nci.webapp.AlzApp.repository.ReportRepository;
import com.nci.webapp.AlzApp.repository.UserRepository;
import com.nci.webapp.AlzApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReportRepository reportRepository;


    @GetMapping("/users")
    public String users(Model model){
        List<User> users = userRepository.findAll();
        List<Report> reports = reportRepository.findAll();
        int reportQtt = reports.size();

        model.addAttribute("quantityReports", reportQtt);
        model.addAttribute("users", users);
        return "user/users";
    }
}
