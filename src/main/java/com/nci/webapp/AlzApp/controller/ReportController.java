package com.nci.webapp.AlzApp.controller;

import com.nci.webapp.AlzApp.dto.ReportSearchParams;
import com.nci.webapp.AlzApp.dto.RequestNewReport;
import com.nci.webapp.AlzApp.model.Report;
import com.nci.webapp.AlzApp.model.User;
import com.nci.webapp.AlzApp.repository.ReportRepository;
import com.nci.webapp.AlzApp.repository.UserRepository;
import com.nci.webapp.AlzApp.service.ReportServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/report")
@Slf4j
public class ReportController {


    //Setting the list to filter the search
    private static final String MEDICATION = "Medication";
    private static final String SIDE_EFFECTS = "Side effects";
    //show report form
    private static final List<String> FILTER_KEYS = Arrays.asList(MEDICATION, SIDE_EFFECTS);


    //save report form
    @Autowired //inject
    private ReportRepository reportRepository;
    //UPDATE
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReportServiceImp reportService;

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

    @PostMapping("/save")
    public String saveReport(@Valid RequestNewReport request, Model model) {
        int reportCounter;

        try {
            //discover logged user username
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userRepository.findByUsername(username);

            //Getting the report inputs
            Report report = request.toReport();


            // Calling external API
            String drugName = report.getDrug();
            List<String> sideEffectsList;
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

            //report counter
            List<Report> reportList = reportRepository.findAllByUser(username);
            reportCounter = reportList.size();
            user.setQuantityReports(reportCounter);
            System.out.println("Report counter" + user.getQuantityReports());
            userRepository.save(user);

        } catch (Exception ex) {
            //duplicated date exception
            String message = "You already did a report for this date. Please insert a new date for the new report.";
            model.addAttribute("error", message);

            //setting the lists again
            HashMap<String, Integer> behaviour = reportService.behaviourList();
            model.addAttribute("behaviour", behaviour);

            HashMap<String, Integer> symptom = reportService.symptomsList();
            model.addAttribute("symptom", symptom);

            return "report/new-report";

        }

        if(reportCounter < 7){

            return "redirect:/report/table";
        }

        return "redirect:/report/dashboard";
    }

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

        //report counter
        List<Report> reportList = reportRepository.findAllByUser(username);
        int reportCounter = reportList.size();
        user.setQuantityReports(reportCounter);
        System.out.println("Report counter" + user.getQuantityReports());
        userRepository.save(user);

        return "redirect:/report/table";
    }

    //delete
    @GetMapping("/delete/{id}")
    public String deleteReport(@PathVariable(value = "id") long id, Model model) {

        Report report = reportService.getReportById(id);

        // call delete employee method
        reportService.deleteReportById(id);

        String message = "Success! Report deleted.";
        model.addAttribute("message", message);

        return "forward:/report/table";


    }

    //dashboard view
    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        //discover logged user username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);

        int reportCounter = user.getQuantityReports();
         if(reportCounter < 7){
             String messageDash = "There are not enough data do display at the dashboard";
             model.addAttribute("message", messageDash);
         }

        return "/report/dashboard";
    }

    //table view
    @GetMapping("/table")
    public String table(@Valid ReportSearchParams request, Model model, Principal principal) {

        List<Report> reports = new ArrayList<>();
        HashMap<String, Integer> behaviour = new HashMap<>();

//        log.info("principal: {}", principal.toString());
        //getting report by user role type - admin has access to all the reports, and user their own reports
        if (principal.getName().equals("admin")) {
            reports = reportRepository.findAll().stream().sorted(Comparator.comparing(Report::getDate).reversed()).collect(Collectors.toList());
        } else {
            reports = reportRepository.findAllByUser(principal.getName());
            //sorting list
            reports = reports.stream().sorted(Comparator.comparing(Report::getDate).reversed()).collect(Collectors.toList());
        }


        /**
         * The search function
         */

        //create the dropdown list
        String message = "";

        log.info("search params: {}", request.toString()); //cheking parameters searched on log

        //seting the switch case based on the user choice
        if (request.getKey() != null) {

                switch (request.getKey()) {
                    case MEDICATION:
                        reports = reports.stream().filter(report -> report.getDrug().equalsIgnoreCase(request.getValue())).collect(Collectors.toList());
                        break;
                    case SIDE_EFFECTS:
                        reports = reports.stream().filter(report -> report.getSideEffects().stream().anyMatch(request.getValue()::equalsIgnoreCase)).collect(Collectors.toList());
                        break;
                    default:
                        log.info("no key selected");


            }
        }else if(request.getValue().isEmpty()){
                message = "Search validation: Please, select a filter and or a key-word";
                System.out.println(message);

        }
        model.addAttribute("error", message);
        model.addAttribute("filterList", FILTER_KEYS);
        model.addAttribute("reports", reports);
        return "report/table";
    }

}