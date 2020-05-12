package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping("/smartwizard")
    public String sssm(){

        return "test/smartWizard";
    }

    @GetMapping("/chatbot")
    public String chatbot(){

        return "test/chatbot";
    }
}
