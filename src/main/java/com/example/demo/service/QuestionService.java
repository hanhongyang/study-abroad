package com.example.demo.service;

import com.example.demo.model.Question;

import java.util.List;

public interface QuestionService {
    //查询某个section里的所有question
    List<Question> getAllBySectionId(Integer sectionId);
    //发布question
    void publish(String title,
                 String description,
                 Long gmtCreate,
                 Long gmtModify,
                 Integer creator,
                 String tag,
                 Integer sectionId);
}
