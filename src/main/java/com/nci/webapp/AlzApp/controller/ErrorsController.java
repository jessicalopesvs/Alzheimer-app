package com.nci.webapp.AlzApp.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorsController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String errorMessage ="";
        String message ="";


        model.addAttribute("error", errorMessage);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                //Error 400
                errorMessage = "Error code: " + statusCode;
                message = "The page you are searching does not exist!";
                model.addAttribute("error", errorMessage);
                model.addAttribute("message", message);

                return "error";
            }else if(statusCode == HttpStatus.FORBIDDEN.value()) {
                //403
                errorMessage = "Error code: " + statusCode + " - You don't have permission to access this area";
                message = "You're not alowed here!";
                model.addAttribute("error", errorMessage);
                model.addAttribute("message", message);
                return "error";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                //500
                errorMessage = "Error code: " + statusCode;
                message = "Our team is working on this issue";
                model.addAttribute("error", errorMessage);
                model.addAttribute("message", message);
                return "error";
            }else {
                errorMessage = "Error code: " + statusCode;
                model.addAttribute("error", errorMessage);
                return "error";
            }
        }

        return "error";
    }

}
