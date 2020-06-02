package com.example.demo.controller;

import com.example.demo.model.Msg;
import com.example.demo.model.News;
import com.example.demo.service.Impl.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class NewsController {
    @Autowired
    private NewsServiceImpl newsService;
    //新闻轮播
    @GetMapping("/news")
    @ResponseBody
    public Msg news(){
        List<News> lastNews=newsService.findLastNews();
        return Msg.success().add("lastNews",lastNews);
    }
}
