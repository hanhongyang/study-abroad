package com.example.demo.mapper;

import com.example.demo.model.Notification;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface NotificationMapper {
    //获取消费者的消息列表
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
    List<Notification> getAllByConsumerOrderByGmtCreateDesc(Integer userId);

    int getCountByConsumerAndStatusIsUnread(int status);
}
