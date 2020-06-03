package com.example.demo.controller;

import com.example.demo.model.Msg;
import com.example.demo.model.News;
import com.example.demo.service.Impl.NewsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class NewsController {
    @Autowired
    private NewsServiceImpl newsService;
    @Autowired
    RedisTemplate redisTemplate;
    //新闻轮播
    @GetMapping("/news")
    @ResponseBody
    public Msg news(){
        List<News> lastNews=new ArrayList<>();
        if(!redisTemplate.hasKey("lastNews")){
            redisTemplate.opsForList().leftPush("lastNews",newsService.findLastNews());
            lastNews=(List<News>)redisTemplate.opsForList().leftPop("lastNews");
            System.out.println("1");
            System.out.println(lastNews);
        }else {
            lastNews=(List<News>)redisTemplate.opsForList().leftPop("lastNews");
            System.out.println("2");
            System.out.println(lastNews);
        }
        if(lastNews==null){
            log.info("新闻获取失败！-----------------------------");
        }
        return Msg.success().add("lastNews",lastNews);
    }
}
