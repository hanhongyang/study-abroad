package com.example.demo.mapper;

import com.example.demo.model.Comment;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface CommentMapper {
    //查询某个问题的所有一级评论携带user
    @Select("select * from comment where parent_id=#{id} and type=1 order by gmt_modify desc")
    @Results(id="commentWithUserMap",value={
            @Result(property = "id", column = "id", jdbcType= JdbcType.INTEGER),
            @Result(property = "content", column = "content"),
            @Result(property = "type", column = "type"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModify", column = "gmt_modify"),
            @Result(property = "commentator", column = "commentator"),
            @Result(property = "commentCount", column = "comment_count"),
            @Result(property = "viewCount", column = "view_count"),
            @Result(property = "likeCount", column = "like_count"),
            @Result(property = "parentId", column = "parent_id"),
            @Result(property = "user", column = "commentator",one = @One(select = "com.example.demo.mapper.UserMapper.selectByPrimaryKey"))
    })
    List<Comment> getFirstCommentByQuestionIdWithUser(@Param("id") Integer id);
    //插入评论并返回id
    @Insert("insert into comment(parent_id,type,commentator,gmt_create,gmt_modify,content) values(#{parentId},#{type},#{commentator},#{gmtCreate},#{gmtModify},#{content})")
    void addComment(Integer parentId,
                    Integer type,
                    Integer commentator,
                    Long gmtCreate,
                    Long gmtModify,
                    String content );
    //查询某个评论的父评论
    @Select("select * from comment where id=#{parentId}")
    @Results(id="commentMap",value={
            @Result(property = "id", column = "id", jdbcType= JdbcType.INTEGER),
            @Result(property = "content", column = "content"),
            @Result(property = "type", column = "type"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModify", column = "gmt_modify"),
            @Result(property = "commentator", column = "commentator"),
            @Result(property = "commentCount", column = "comment_count"),
            @Result(property = "viewCount", column = "view_count"),
            @Result(property = "likeCount", column = "like_count"),
            @Result(property = "parentId", column = "parent_id"),
    })
    Comment getParentByParentId(Integer parentId);

    //评论数+1
    @Update("update comment set comment_count=comment_count+1 where id=#{id}")
    void addCommentCount(@Param("id")Integer id);

    //查询此条评论的问题Id
    @Select("select parent_id from comment where id=#{id}")
    Integer getParentIdById(Integer id);

    //查询某个评论的所有二级评论携带user
    @Select("select * from comment where parent_id=#{id} and type=2 order by gmt_modify desc")
    @ResultMap("commentWithUserMap")
    List<Comment> getSecondCommentByCommentIdWithUser(Integer id);

    //查询某个评论
    @Select("select * from comment where id=#{id}")
    @ResultMap("commentMap")
    Comment getById(Integer id);

    //点赞
    @Update("update comment set like_count=like_count+#{one} where id=#{id}")
    void thumbsUp(Integer id, int one);
}
