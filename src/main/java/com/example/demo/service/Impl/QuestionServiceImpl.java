package com.example.demo.service.Impl;

import com.example.demo.mapper.QuestionMapper;
import com.example.demo.model.Question;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    //查询某个section里的所有question
    @Override
    public List<Question> getAllBySectionId(Integer sectionId) {
        return questionMapper.getAllBySectionId(sectionId);
    }
}
