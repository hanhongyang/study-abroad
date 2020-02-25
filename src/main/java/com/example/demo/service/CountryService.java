package com.example.demo.service;

import com.example.demo.model.Country;

import java.util.List;

public interface CountryService {
    //批量插入数据
    void batchSave();
    List<Country> getAll();
}
