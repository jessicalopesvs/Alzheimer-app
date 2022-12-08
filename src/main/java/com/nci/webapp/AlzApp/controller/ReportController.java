package com.nci.webapp.AlzApp.controller;

import com.nci.webapp.AlzApp.dto.RequestNewReport;
import com.nci.webapp.AlzApp.model.Report;
import com.nci.webapp.AlzApp.model.User;
import com.nci.webapp.AlzApp.repository.ReportRepository;
import com.nci.webapp.AlzApp.repository.UserRepository;
import com.nci.webapp.AlzApp.service.ReportServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
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
        HashMap<String, Integer> behaviour = reportService.behaviourList();

        //setting list of Symptoms field
        HashMap<String, Integer> symptom = reportService.symptomsList();

        //setting attributes
        model.addAttribute("behaviour", behaviour);
        model.addAttribute("symptom", symptom);

        return "report/new-report";
    }

    //save report form

    @PostMapping("/new-report")
    public String NewReport(@Valid RequestNewReport request, BindingResult result, Model model) {

        try {
            //discover logged user username
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userRepository.findByUsername(username);
            //Getting the report inputs
            Report report = request.toReport();


            // Calling external API
            String drugName = report.getDrug();
            List<String> sideEffectsList = new ArrayList<>();
            sideEffectsList = reportService.sideEffectsApi(drugName);

            if (sideEffectsList.isEmpty()) {
                String sideEffectEmpty = "not found";
                sideEffectsList.add(sideEffectEmpty);
            }

            //setting user and sideeffects list
            report.setUser(user);
            report.setSideEffects(sideEffectsList);

            //saving report
            reportService.saveReport(report);

        } catch (Exception ex) {
            //duplicated date exception
            String exceptionMessage = ex.getMessage();
            String message = "You already did a report for this date. Please insert a new date for the new report.";
            model.addAttribute("error", message);

            //setting the lists again
            HashMap<String, Integer> behaviour = reportService.behaviourList();
            model.addAttribute("behaviour", behaviour);

            HashMap<String, Integer> symptom = reportService.symptomsList();
            model.addAttribute("symptom", symptom);

            return "report/new-report";

        }
        return "redirect:/report/dashboard";
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


        //Calling the API to get the new side effects
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
    public String deleteEmployee(@PathVariable(value = "id") long id, Model model) {

        Report report = reportService.getReportById(id);

        // call delete employee method
        reportService.deleteReportById(id);

        String message = "Success! Report deleted.";
        model.addAttribute("message", message);

        return "forward:/report/table";
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
            //sorting list
            reports = reports.stream().sorted(Comparator.comparing(Report::getDate)).collect(Collectors.toList());
        }

        // TODO: If doesnt want to filter just skip the switch case below
        List<String> filter = new ArrayList<>();
        filter.add("medication");
        filter.add("side-effects");
        model.addAttribute("filterList", filter);
        System.out.println(filter.toString());


        String searchType = "key";
        String searchValue = "value";
        model.addAttribute("search", searchValue);
        model.addAttribute("key", searchType);

        model.addAttribute("reports", reports);
        return "report/table";
    }


    @GetMapping("/table/search?type={type}&search={search}")
    public String search(@Param(value = "type") String searchType,
                         @Param(value = "search") String searchValue,
                         Model model, Principal principal, @PathVariable String search) {
        List<Report> reports = new ArrayList<>();

        // TODO: If doesnt want to filter just skip the switch case below
        List<String> filter = new ArrayList<>();
        filter.add("medication");
        filter.add("side-effects");
        model.addAttribute("filterList", filter);


//        String searchType = "key";
//        String searchValue = "value";
        model.addAttribute("search", searchValue);
        model.addAttribute("key", searchType);


        log.info("principal: {}", principal.toString());

        if (principal.getName().equals("admin")) {
            reports = reportRepository.findAll().stream().sorted(Comparator.comparing(Report::getDate)).collect(Collectors.toList());
        } else {
            reports = reportRepository.findAllByUser(principal.getName());
            //sorting list
            reports = reports.stream().sorted(Comparator.comparing(Report::getDate)).collect(Collectors.toList());
        }


        switch (searchType) {
            case "medication":
                reports = reports.stream().filter(report -> report.getDrug().equalsIgnoreCase(searchValue)).collect(Collectors.toList());
                break;
            case "side-effects":
                reports = reports.stream().filter(report -> report.getSideEffects().stream().anyMatch(searchValue::equalsIgnoreCase)).collect(Collectors.toList());
                break;
        }

        return "forward:/report/table";
    }

    /**
     * SETTIN UP PAGE
     **/


    @GetMapping("/page/{pageNo}")
    public String findPage(@PathVariable(value = "pageNo") int pageNo,
                           @RequestParam("sortField") String sortField,
                           @RequestParam("sortDir") String sortDir,
                           Model model) {
        int pageSize = 10;

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