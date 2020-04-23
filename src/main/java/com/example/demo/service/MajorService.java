package com.example.demo.service;

import com.example.demo.model.Major;

import java.util.List;

public interface MajorService {
    //查询某个学校的所以专业
    List<Major> getAllBySchoolId(Integer schoolId);
    //查询某个学校的某个专业
    Major getById(Integer id);
}
