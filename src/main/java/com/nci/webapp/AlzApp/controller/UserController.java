package com.nci.webapp.AlzApp.controller;


import com.nci.webapp.AlzApp.model.Report;
import com.nci.webapp.AlzApp.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")

public class UserController {

    @Autowired
    private final ReportRepository repository;

    public UserController(ReportRepository repository) {
        this.repository = repository;
    }


    //criar método para retornar a view
    public String Home (Model model, Principal principal){

        //add na lista
        List<Report> reports = repository.findAllByUser(principal.getName());
        model.addAttribute("reports", reports);
        return "user/home";
    }

    //filtro
//    @GetMapping("pedido/{status}")
//    public String statusFilter (@PathVariable("status") String status, Model model, Principal principal){
//
//
//        //add na lista
//        List<Pedido> pedidos = repository.findByStatusAndUser(StatusPedido.valueOf(status.toUpperCase()), principal.getName());
//        model.addAttribute("pedidos", pedidos);
//        model.addAttribute("status", status);
//        return "user/home";
//    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String onError(){
        return "redirect:/user/home";
    }
}
