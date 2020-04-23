package com.example.demo.service.Impl;

import com.example.demo.mapper.MajorMapper;
import com.example.demo.model.Major;
import com.example.demo.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MajorServiceImpl implements MajorService {
    @Autowired
    MajorMapper majorMapper;

    @Override
    public List<Major> getAllBySchoolId(Integer schoolId) {
        return majorMapper.getAllBySchoolId(schoolId);
    }

    @Override
    public Major getById(Integer id) {
        return majorMapper.getById(id);
    }
}
