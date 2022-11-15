package com.nci.webapp.AlzApp.controller;

import com.nci.webapp.AlzApp.dto.RequestNewReport;
import com.nci.webapp.AlzApp.model.Report;
import com.nci.webapp.AlzApp.repository.ReportRepository;
import com.nci.webapp.AlzApp.service.NewReportService;
import com.nci.webapp.AlzApp.service.TableService;
import org.hibernate.mapping.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.text.TabExpander;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/report")
public class ReportFormController {

    @Autowired //inject
    private ReportRepository reportRepository;

    @GetMapping("/new")
    public String ReportForm (RequestNewReport request){

        return "report/new-report";
    }

    @PostMapping("/new-report")
    public String NewReport (@Valid RequestNewReport request, BindingResult result){

        //declare the service
        NewReportService service = new NewReportService(reportRepository);
        //calling the service
        service.NewReport(request,result);

        return "redirect:/table" ;
    }

    @GetMapping("/table")
    public String table (Model model){

        List<Report> reports = reportRepository.findAll();
        model.addAttribute("reports", reports);

        return "report/table";
    }
}
