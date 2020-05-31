package com.example.demo.mapper;

import com.example.demo.model.Notification;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface NotificationMapper {
    //获取用户的系统推荐消息列表
    @Select("select * from notification where consumer=#{userId} and type=3 order by gmt_create desc")
    @Results(id="Notification",value={
            @Result(property = "id", column = "id", jdbcType= JdbcType.INTEGER),
            @Result(property = "producer", column = "producer"),
            @Result(property = "outerId", column = "outer_id"),
            @Result(property = "type", column = "type"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "consumer", column = "consumer"),
            @Result(property = "status", column = "status"),
            @Result(property = "outerTitle", column = "outer_title"),
            @Result(property = "producerName", column = "producer_name"),
            @Result(property = "content", column = "content")
            })
    List<Notification> getAllRecommendNotificationByConsumerOrderByGmtCreateDesc(Integer userId);

    //获取用户未读消息数
    @Select("select count(*) from notification where status=0 and consumer=#{userId}")
    int getCountByConsumerAndStatusIsUnread(int status,int userId);
    //插入推荐通知
    @Insert("insert into notification(type,consumer,gmt_create,status,outer_title,content) values(#{type},#{consumer},#{gmtCreate},#{status},#{outerTitle},#{content})")
    void insertRecommendNotification(Notification recommendNotification);

    //获取用户未读消息前4条
    @Select("select * from notification where consumer=#{userId} and status=#{status} order by gmt_create desc limit 4")
    @ResultMap("Notification")
    List<Notification> getAllRecommendNotificationByConsumerOrderByGmtCreateDescLimit4(int status,int userId);

    //设为已读
    @Update("update notification set status=1 where consumer=#{userId}")
    void updateNotificationStatusByConsumer(int userId);
}
