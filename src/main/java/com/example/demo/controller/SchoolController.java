package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.model.*;
import com.example.demo.service.Impl.CountryServiceImpl;
import com.example.demo.service.Impl.QuestionServiceImpl;
import com.example.demo.service.Impl.SchoolServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@Slf4j
public class SchoolController {
    @Autowired
    SchoolServiceImpl schoolService;
    @Autowired
    CountryServiceImpl countryService;
    @Autowired
    QuestionServiceImpl questionService;
    @GetMapping("/schools")
    public String schools(HttpServletRequest request,
                          HttpServletResponse response,
                          @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                          Model model,
                          @RequestParam(value = "countryId",required = false)Integer countryId){
        //判断是否选择国家
        if (countryId == null) {
            //未选择国家直接返回所有学校
            //在查询之前只需要调用，传入页码，以及每页的大小
            PageHelper.startPage(pageNum,9);
            //startpage后紧跟的查询就是分页查询
            //获取所有学校携带国家信息
            List<School> schoolList=schoolService.getAllWithCountry();
            //用pageinfo包装查询结果，只需要将pageinfo交给页面就行了
            PageInfo pageInfo=new PageInfo(schoolList);
            model.addAttribute("pageInfo",pageInfo);
            List<Country> countryList=countryService.popCountries();
            model.addAttribute("countryList",countryList);
        }else {
            //选择国家则返回此国家学校
            //在查询之前只需要调用，传入页码，以及每页的大小
            PageHelper.startPage(pageNum,9);
            //startpage后紧跟的查询就是分页查询
            //获取所有学校携带国家信息
            List<School> schoolList=schoolService.getAllWithCountryByCountryId(countryId);
            //用pageinfo包装查询结果，只需要将pageinfo交给页面就行了
            PageInfo pageInfo=new PageInfo(schoolList);
            Msg msg;
            msg=Msg.success().add("schoolList",schoolList);
            // 返回 JSON
            response.setContentType("application/json");
            response.setStatus(200);
            response.setCharacterEncoding("utf-8");
            PrintWriter writer = null;
            try {
                writer = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            writer.write(JSON.toJSONString(msg));
            writer.close();
            return null;
        }

        return "school/schools";
    }

    @GetMapping("/school/{schoolId}")
    public String school(@PathVariable("schoolId")Integer schoolId,Model model){

        School school=schoolService.getById(schoolId);
        //相关问答
        Question question=questionService.getByIdWithUser(1);
        model.addAttribute("question",question);
        model.addAttribute("school",school);
        return "school/school";
    }
}
