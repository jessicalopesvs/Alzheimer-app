package com.nci.webapp.AlzApp.controller;


import com.nci.webapp.AlzApp.model.Symptoms;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {

        return "home";
    }

}
