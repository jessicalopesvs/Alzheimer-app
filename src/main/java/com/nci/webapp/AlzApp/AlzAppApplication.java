package com.nci.webapp.AlzApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@SpringBootApplication
@EnableCaching
public class AlzAppApplication {

    //setting the index page
    public void addViewController(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/home.html");
    }

    public static void main(String[] args) {
        SpringApplication.run(AlzAppApplication.class, args);
    }

}
