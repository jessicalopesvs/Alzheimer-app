package com.nci.webapp.AlzApp.controller;

import com.nci.webapp.AlzApp.dto.RequestNewReport;
import com.nci.webapp.AlzApp.model.Emotions;
import com.nci.webapp.AlzApp.model.Report;
import com.nci.webapp.AlzApp.model.Symptoms;
import com.nci.webapp.AlzApp.repository.ReportRepository;
import com.nci.webapp.AlzApp.service.NewReportService;
import net.bytebuddy.description.modifier.SynchronizationState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/report")
public class ReportFormController {

    @Autowired //inject
    private ReportRepository reportRepository;

    NewReportService service = new NewReportService(reportRepository);


    @GetMapping("/new")
    public String ReportForm(RequestNewReport request, Model model) {

        //setting the Emotions list field
        HashMap<String, Integer> behaviour = new HashMap<>();
        Arrays.stream(Emotions.values()).forEach(emotions -> behaviour.put(emotions.getDisplayValue(), 0));

        System.out.println(behaviour.toString());

        //setting list of Symptoms field
        HashMap<String, Integer> symptomsList = new HashMap<>();
        Arrays.stream(Symptoms.values()).forEach(symptom -> symptomsList.put(symptom.getDisplayValue(), 0));

        System.out.println(symptomsList.toString());


        //setting attributes
        model.addAttribute("behaviour", behaviour);
        model.addAttribute("symptoms", symptomsList);

//        service.setEmotionsList(model);

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
