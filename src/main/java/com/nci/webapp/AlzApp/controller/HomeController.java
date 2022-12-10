package com.nci.webapp.AlzApp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model){

        String test = "oi";
        String text ="<p>Alzheimer disease is a degenerative disease of the brain that causes dementia," +
                " which is a gradual loss of memory, judgment, and ability to function. " +
                "This disorder usually appears in people older than age 65, but less common forms of " +
                "the disease appear earlier in adulthood.</p><p>Memory loss is the most common sign of" +
                " Alzheimer disease. Forgetfulness may be subtle at first, but the loss of memory worsens " +
                "over time until it interferes with most aspects of daily living. Even in familiar settings, " +
                "a person with Alzheimer disease may get lost or become confused. Routine tasks such as preparing" +
                " meals, doing laundry, and performing other household chores can be challenging. Additionally, it may become" +
                " difficult to recognize people and name objects. Affected people increasingly require help with dressing, eating, " +
                "and personal care.</p><p>As the disorder progresses, some people with Alzheimer disease experience personality and behavioral changes and have trouble interacting in a socially appropriate manner. Other common symptoms include agitation, restlessness, withdrawal, and loss of language skills. People with this disease usually require total care during the advanced stages of the disease.</p><p>Affected individuals usually survive 8 to 10 years after the appearance of symptoms, but the course of the disease can range from 1 to 25 years. Survival is usually shorter in individuals diagnosed after age 80 than in those diagnosed at a younger age. Death usually results from pneumonia, malnutrition, or general body wasting (inanition).</p><p>Alzheimer disease can be classified as early-onset or late-onset. The signs and symptoms of the early-onset form appear between a person's thirties and mid-sixties, while the late-onset form appears during or after a person's mid-sixties. The early-onset form is much less common than the late-onset form, accounting for less than 10 percent of all cases of Alzheimer disease.</p>";
        model.addAttribute("text", test);
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
