package com.example.demo.controller;

import com.example.demo.model.Country;
import com.example.demo.model.Msg;
import com.example.demo.service.CountryService;
import com.example.demo.service.Impl.CountryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CountryController {
    @Autowired
    CountryServiceImpl countryService;

    /**
     * 查询所有国家数据。
     * @return 国家列表
     */
    @ResponseBody
    @GetMapping("/countries")
    public Msg getAllCountries(){
        List<Country> countries=countryService.getAll();
        return Msg.success().add("countries",countries);
    }

    /**
     * 查询某个国家数据
     * @param countryId
     * @return
     */
    @ResponseBody
    @GetMapping("/country/{countryId}")
    public Msg getById(@PathVariable("countryId")Integer countryId){
        Country country=countryService.getById(countryId);
        return Msg.success().add("country",country);
    }

}
