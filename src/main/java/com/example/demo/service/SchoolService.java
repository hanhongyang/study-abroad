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
}
