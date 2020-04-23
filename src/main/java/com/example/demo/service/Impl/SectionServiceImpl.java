package com.example.demo.service.Impl;

import com.example.demo.mapper.SectionMapper;
import com.example.demo.model.Section;
import com.example.demo.service.SectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SectionServiceImpl implements SectionService {
    @Autowired
    SectionMapper sectionMapper;

    @Override
    public List<Section> getAll() {
        return sectionMapper.getAll();
    }

    @Override
    public List<Section> getAllWithSchool() {
        return sectionMapper.getAllWithSchool();
    }

    @Override
    public Section getByIdWithSchool(Integer sectionId) {
        return sectionMapper.getByIdWithSchool(sectionId);
    }

    //板块问题数+1
    @Override
    public void addQuestionCount(Integer sectionId) {
         sectionMapper.addQuestionCount(sectionId);
    }
}
