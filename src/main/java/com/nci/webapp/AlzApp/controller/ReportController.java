package com.nci.webapp.AlzApp.controller;

import com.nci.webapp.AlzApp.dto.RequestNewReport;
import com.nci.webapp.AlzApp.model.Emotions;
import com.nci.webapp.AlzApp.model.Report;
import com.nci.webapp.AlzApp.model.Symptoms;
import com.nci.webapp.AlzApp.model.User;
import com.nci.webapp.AlzApp.repository.ReportRepository;
import com.nci.webapp.AlzApp.repository.UserRepository;
import com.nci.webapp.AlzApp.service.ReportServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/report")
@Slf4j
public class ReportController {

    @Autowired //inject
    private ReportRepository reportRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReportServiceImp reportService;

    //show report form

    @GetMapping("/new")
    public String ReportForm(RequestNewReport request, Model model) {

        //setting the Behaviour list field
        HashMap<String, Integer> behaviour = new HashMap<>();
        Arrays.stream(Emotions.values()).forEach(emotions -> behaviour.put(emotions.getDisplayValue(), 0));

        //setting list of Symptoms field
        HashMap<String, Integer> symptom = new HashMap<>();
        Arrays.stream(Symptoms.values()).forEach(s -> symptom.put(s.getDisplayValue(), 0));

        //setting attributes
        model.addAttribute("behaviour", behaviour);
        model.addAttribute("symptom", symptom);

        return "report/new-report";
    }

    //save report form

    @PostMapping("/new-report")
    public String NewReport(@Valid RequestNewReport request, BindingResult result, Model model) {

        //discover logged user username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
        //Getting the report inputs
        Report report = request.toReport();

//
//        java.util.Date date = report.getDate();
//
//        LocalDate converted = new Date(date.getTime()).toLocalDate();
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String text = converted.format(formatter);
//        LocalDate parsedDate = LocalDate.parse(text, formatter);
//
//        List<Report> userReports = user.getReports();
//
//        boolean hasDate = userReports.stream().filter(d -> d.getDate().equals(parsedDate.toString())).findAny().isPresent();
//        System.out.println(parsedDate);
//        System.out.println(hasDate);

//        if (hasDate != true) {
//            result.rejectValue("date", null, "There is already a report with that date");
//        }
//
//        if (result.hasErrors()) {
//            return "report/new-report";
//        }


        // TODO: Call external API
        //Calling the API
        String drugName = report.getDrug();
        List<String> sideEffectsList = new ArrayList<>();
        sideEffectsList = reportService.sideEffectsApi(drugName);

        //setting user and sideeffects list
        report.setUser(user);
        report.setSideEffects(sideEffectsList);

        //saving report

        reportService.saveReport(report);

        return "redirect:/report/table";
    }


    //UPDATE

    /**
     * UPDATE FORM
     **/

    @GetMapping("/edit/{id}")
    public String updateReportForm(@PathVariable(value = "id") long id, Report report, BindingResult result, Model model) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
        //get report from service by id
        report = reportService.getReportById(id);


        //validating user - if user is different from the report user, it will deny the access
        if (user != report.getUser()) {
            int statusCode = 403;
            String errorMessage = "Error code: " + statusCode + " - You don't have permission to access this area";
            ;
            String message = "You're not alowed here!";
            model.addAttribute("error", errorMessage);
            model.addAttribute("message", message);
            return "error";
        }

        //setting the Behavior list field
        HashMap<String, Integer> behaviour = new HashMap<>();
        behaviour.putAll(report.getBehaviour());

        //setting list of Symptoms field
        HashMap<String, Integer> symptom = new HashMap<>();
        symptom.putAll(report.getSymptom());

        //setting attributes
        model.addAttribute("behaviour", behaviour);
        model.addAttribute("symptom", symptom);

        //set report as a model to fill the form with the data

        model.addAttribute("report", report);

        return "report/update";

    }

    /**
     * UPDATE SAVE
     **/

    @PostMapping("/update-report/")
    public String update(@Valid RequestNewReport request, BindingResult result, Model model) {


        //discover logged user username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);

        //Getting the report by id inputs
        Report newData = request.toReport();


        // TODO: Call external API
        //Calling the API to get the new side-effect
        String drugName = newData.getDrug();
        List<String> sideEffectsList = new ArrayList<>();
        sideEffectsList = reportService.sideEffectsApi(drugName);

        //setting user and sideeffects list
        newData.setUser(user);
        newData.setSideEffects(sideEffectsList);

        //saving report

        Report report = reportService.getReportById(newData.getId());
        report = newData;

        reportService.saveReport(report);

        return "redirect:/report/table";
    }


    //delete
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {

        // call delete employee method
        reportService.deleteReportById(id);

        return "redirect:/";
    }

    //VIEW DATA

    @GetMapping("/table")
    public String table(Model model, Principal principal) {
        List<Report> reports = new ArrayList<>();
        log.info("principal: {}", principal.toString());

        if (principal.getName().equals("admin")) {
            reports = reportRepository.findAll().stream().sorted(Comparator.comparing(Report::getDate)).collect(Collectors.toList());
        } else {
            reports = reportRepository.findAllByUser(principal.getName());
            reports = reports.stream().sorted(Comparator.comparing(Report::getDate)).collect(Collectors.toList());
        }
        model.addAttribute("reports", reports);
        return "report/table";
    }

    /**SETTIN UP PAGE**/


    @GetMapping("/page/{pageNo}")
    public String findPage(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 15;

        Page<Report> page = reportService.findPage(pageNo, pageSize, sortField, sortDir);
        List<Report> listReports = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listReports", listReports);
        return "index";
    }
}