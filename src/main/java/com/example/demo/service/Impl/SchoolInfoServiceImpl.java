package com.example.demo.service.Impl;

import com.example.demo.mapper.SchoolInfoMapper;
import com.example.demo.model.SchoolInfo;
import com.example.demo.service.SchoolInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolInfoServiceImpl implements SchoolInfoService {
    @Autowired
    SchoolInfoMapper schoolInfoMapper;
    @Override
    public void add() {

    }

    @Override
    public SchoolInfo getBySchoolId(Integer schoolId) {
        return schoolInfoMapper.getBySchoolId(schoolId);
    }
}
