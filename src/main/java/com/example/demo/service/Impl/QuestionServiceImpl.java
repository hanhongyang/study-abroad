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
    public List<Question> getAllBySectionIdWithUser(Integer sectionId) {
        return questionMapper.getAllBySectionIdWithUser(sectionId);
    }

    @Override
    public void publish(String title, String description, Long gmtCreate, Long gmtModify, Integer creator, String tag, Integer sectionId) {
    questionMapper.addQuestion(title,description,gmtCreate,gmtModify,creator,tag,sectionId);
    }

    @Override
    public Question getByIdWithUser(Integer id) {
        return questionMapper.getByIdWithUser(id);
    }

    @Override
    public void addViewCount(Integer id) {
        questionMapper.addViewCount(id);
    }

    @Override
    public Question getById(Integer id) {
        return questionMapper.getById(id);
    }
}
