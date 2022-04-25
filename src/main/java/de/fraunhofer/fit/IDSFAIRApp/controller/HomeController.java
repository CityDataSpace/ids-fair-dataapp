package de.fraunhofer.fit.IDSFAIRApp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Value("classpath:AppResource.ttl")
    Resource resourceFile;

    @RequestMapping("/")
    public String home() {
        return "redirect:/swagger";
    }



}