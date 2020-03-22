package com.example.demo.service;

import com.example.demo.model.Question;

import java.util.List;

public interface QuestionService {
    //查询某个section里的所有question
    List<Question> getAllBySectionIdWithUser(Integer sectionId);
    //发布question
    void publish(String title,
                 String description,
                 Long gmtCreate,
                 Long gmtModify,
                 Integer creator,
                 String tag,
                 Integer sectionId);
    //id查询question
    Question getByIdWithUser(Integer id);
    //累加阅读数
    void addViewCount(Integer id);
    //查询某个问题
    Question getById(Integer id);
}
