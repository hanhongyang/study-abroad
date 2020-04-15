package com.example.demo.service;

import com.example.demo.model.Comment;

import java.util.List;

public interface CommentService {
    //查询某个问题的所有评论
    List<Comment> getFirstCommentByQuestionIdWithUser(Integer id);
    //查询某个评论的所有二级评论
    List<Comment> getSecondCommentByCommentIdWithUser(Integer id);
    //回复
    void reply(Integer parentId,Integer type,Integer commentator,Long gmtCreate,Long gmtModify,String content);
    //点赞
    void thumbsUp(Integer id,String like);

}
