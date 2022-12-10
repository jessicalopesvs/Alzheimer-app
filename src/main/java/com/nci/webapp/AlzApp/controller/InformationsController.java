package com.nci.webapp.AlzApp.controller;

import com.nci.webapp.AlzApp.api.Medline;
import com.nci.webapp.AlzApp.config.RestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.Arrays;
import java.util.List;

@Controller
public class InformationsController {

    @Autowired
    RestConfig restConfig;

    @GetMapping ("/knowing-the-disease")
    public String informations(Model model){

        Medline api = restConfig.restTemplate().getForObject("https://medlineplus.gov/download/genetics/condition/alzheimer-disease.json", Medline.class);

        List textList = api.getTexts();
        System.out.println(textList.toString());

        List synonyms = api.getSynonyms();
        String synonymsString = Arrays.toString(synonyms.toArray()).replace("[","").replace("]","");
        System.out.println(synonyms.toString());

        model.addAttribute("link", api.getGhr_page());
        model.addAttribute("name", api.getName());
        model.addAttribute("text",textList.get(0));
        model.addAttribute("synonyms", synonymsString);

        return "/informations";
    }







}
