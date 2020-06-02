package com.example.demo.service.Impl;

import com.example.demo.mapper.NewsMapper;
import com.example.demo.model.News;
import com.example.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;

    @Override
    public List<News> findAllNews() {
        return newsMapper.findAllNews();
    }

    @Override
    public List<News> findLastNews() {
        return newsMapper.findLastNews();
    }

}
