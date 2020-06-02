package com.example.demo.service;

import com.example.demo.model.News;

import java.util.List;

public interface NewsService {
    public List<News> findAllNews();

    public List<News> findLastNews();
}
