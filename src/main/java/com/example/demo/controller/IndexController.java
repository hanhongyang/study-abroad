package com.example.demo.controller;

import com.example.demo.mapper.CountryMapper;
import com.example.demo.mapper.SchoolMapper;
import com.example.demo.model.Country;
import com.example.demo.model.School;
import com.example.demo.model.User;
import com.example.demo.service.Impl.SchoolServiceImpl;
import com.example.demo.service.SchoolService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
@Autowired
SchoolServiceImpl schoolService;
    @GetMapping("/")
    public String index(){
        return "index";
    }

}
