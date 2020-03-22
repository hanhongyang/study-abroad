package com.example.demo.service;

import com.example.demo.model.Section;

import java.util.List;

public interface SectionService {

    List<Section> getAll();
    List<Section> getAllWithSchool();

    Section getByIdWithSchool(Integer sectionId);
}
