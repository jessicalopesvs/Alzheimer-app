package com.nci.webapp.AlzApp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/")
    public String index() {
        return "home";
    }

    @RequestMapping("/403")
    public String accessDenied() {
        return "errors/403";
    }

}
