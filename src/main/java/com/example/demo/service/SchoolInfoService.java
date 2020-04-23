package com.example.demo.service;

import com.example.demo.model.SchoolInfo;

public interface SchoolInfoService {

    void add();

    SchoolInfo getBySchoolId(Integer schoolId);
}
