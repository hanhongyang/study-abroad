package com.example.demo.service.Impl;

import com.example.demo.mapper.CommentMapper;
import com.example.demo.model.Comment;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Override
    public List<Comment> getAllByQuestionIdWithUser(Integer id) {
        return commentMapper.getAllByQuestionIdWithUser(id);
    }

    @Override
    public void reply(Integer parentId,Integer type,Integer commentator,Long gmtCreate,Long gmtModify,String content) {
        commentMapper.addComment(parentId,type,commentator,gmtCreate,gmtModify,content);
    }
}
