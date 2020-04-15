package com.example.demo.service;

import com.example.demo.mapper.SchoolMapper;
import com.example.demo.model.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SchoolService {

 void save();
 List<School> getAll();
 School getById(int schoolId);
 //查询所有school携带国家
 List<School> getAllWithCountry();
 //查询某个国家的所有学校携带国家
 List<School> getAllWithCountryByCountryId(Integer countryId);
}
