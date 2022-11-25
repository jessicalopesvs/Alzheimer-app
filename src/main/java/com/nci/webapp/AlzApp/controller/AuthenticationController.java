package com.nci.webapp.AlzApp.controller;

import com.nci.webapp.AlzApp.dto.RequestUser;
import com.nci.webapp.AlzApp.model.User;

import com.nci.webapp.AlzApp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AuthenticationController {

    private UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "user/login";
    }

    @GetMapping("/register")
    public String RegistrationForm(Model model){

        RequestUser user = new RequestUser();
        model.addAttribute("user", user);

        return "user/register";
    }

    //method that validates and save user
    @PostMapping("/register/save")
    public String saveNewAccount (@Valid @ModelAttribute ("user") RequestUser user, BindingResult result, Model model){

        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "user/register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }

    //method that lists all the users
    @GetMapping ("/users")
    public String users(Model model) {
        List<RequestUser> users = userService.findAllUsers();

        model.addAttribute("users", users);
        return "user/users";
    }
}
