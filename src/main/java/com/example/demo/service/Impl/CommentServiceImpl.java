package com.example.demo.service.Impl;

import com.example.demo.enums.CommentTypeEnum;
import com.example.demo.enums.CustomizeErrorCode;
import com.example.demo.exception.CustomizeException;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.model.Comment;
import com.example.demo.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Override
    public List<Comment> getFirstCommentByQuestionIdWithUser(Integer id) {
        //如果问题不存在
        if(questionMapper.getById(id)==null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        return commentMapper.getFirstCommentByQuestionIdWithUser(id);
    }

    @Override
    public List<Comment> getSecondCommentByCommentIdWithUser(Integer id) {
        //如果问题不存在
        if(questionMapper.getById(commentMapper.getParentIdById(id))==null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        //如果回复的父评论未找到
        if(commentMapper.getById(id)==null){
            throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
        }
        return commentMapper.getSecondCommentByCommentIdWithUser(id);
    }

    @Override
    @Transactional//事务管理,报错会回滚
    public void reply(Integer parentId,Integer type,Integer commentator,Long gmtCreate,Long gmtModify,String content) {
        //如果未选中问题或评论
        if(parentId==null||parentId==0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        //如果type不正确
        if(type==null|| !CommentTypeEnum.isExist(type)){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        //如果content为空
        if(StringUtils.isBlank(content)){
            throw new CustomizeException(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }
        //如果回复的是评论
        if (type == CommentTypeEnum.COMMENT.getType()) {
            // 回复评论
            //如果回复的父评论未找到
            if(commentMapper.getParentByParentId(parentId)==null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            // 回复问题
            //如果回复的父评论的问题未找到
            if(questionMapper.getById(commentMapper.getParentByParentId(parentId).getParentId())==null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            //插入评论
            commentMapper.addComment(parentId,type,commentator,gmtCreate,gmtModify,content);
            // 增加评论数
            questionMapper.addCommentCount(commentMapper.getParentIdById(parentId));
            commentMapper.addCommentCount(parentId);
            //更新问题gmt_modify
            questionMapper.gmtModify(gmtModify,questionMapper.getById(commentMapper.getParentIdById(parentId)).getId());
        }else {
            //如果回复的是问题
            //如果回复的问题未找到
            if(questionMapper.getById(parentId)==null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            //插入评论
            commentMapper.addComment(parentId,type,commentator,gmtCreate,gmtModify,content);
            // 增加评论数
            questionMapper.addCommentCount(parentId);
            //更新问题gmt_modify
            questionMapper.gmtModify(gmtModify,questionMapper.getById(parentId).getId());
        }

    }

    @Override
    public void thumbsUp(Integer id,String like) {
        //如果评论未找到
        if(commentMapper.getById(id)==null){
            throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
        }//如果问题未找到
        if(questionMapper.getById(commentMapper.getParentIdById(id))==null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        int one=0;
        if(StringUtils.equals("true",like)){
            //点赞+1
            one=1;
            commentMapper.thumbsUp(id,one);
        }else {
            //点赞-1
            one=-1;
            commentMapper.thumbsUp(id,one);
        }
    }

    @Override
    public Comment getBestAnswerByIdWithUser(Integer id) {
        return commentMapper.getBestAnswerByIdWithUser(id);
    }

}
