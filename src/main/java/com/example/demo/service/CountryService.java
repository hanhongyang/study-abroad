package com.example.demo.service;

import com.example.demo.model.Country;

import java.util.List;

public interface CountryService {
    //批量插入数据
    void batchSave();
    List<Country> getAll();
    //查询国家
    Country getById(Integer countryId);
    //查询热门国家
    List<Country> popCountries();
}
