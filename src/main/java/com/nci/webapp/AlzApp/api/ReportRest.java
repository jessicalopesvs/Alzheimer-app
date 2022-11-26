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
import java.util.List;

@RestController
@RequestMapping("api/reports")
public class ReportRest {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("user-report.json")
    public List<User> getReports(Principal principal){
        String username = "user";
        Sort sort = Sort.by("id").ascending();
        return userRepository.findAll();
    }
}
