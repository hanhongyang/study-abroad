package com.example.demo.mapper;

import com.example.demo.model.User;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface UserMapper {
    //查询所有user
    @Select("select * from user")
    @Results(id="UserMap",value={
            @Result(property = "userId", column = "user_id", jdbcType= JdbcType.INTEGER),
            @Result(property = "password", column = "password"),
            @Result(property = "rule", column = "rule"),
            @Result(property = "countryId", column = "country_id"),
            @Result(property = "email", column = "email"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "name", column = "name"),
            @Result(property = "icon", column = "icon")
    })
    public List<User> getAll();

    //插入一条user数据
    @Insert("insert into user(user_id,password,rule,country_id,email,mobile,birthday,name,icon) " +
            "values(#{user.userId},#{user.password}, #{user.rule},#{user.countryId},#{user.mobile},#{user.birthday}, #{user.name},#{user.icon},)")
    public void insert(User user);

    //批量插入用户数据
    public void batchSave(List<User> users);

    //批量插入随机数据
    public void batchSaveRandom();

    //删除生日不为空的users
    public void deleteByBirthDay();
    @Delete("delete from user where user_id=#{userId} ")
    public void deleteByPrimaryKey(Integer userId);


    //根据userId取User
    @Select("select * from user where user_id=#{userId}")
    @ResultMap("UserMap")
    User selectByPrimaryKey(Integer userId);

    //判断email是否已存在
    @Select("select count(*) from user where email=#{email}")
    public long checkEmail(String email);

    //更新user
    @Update("update user set password=#{user.password}," +
            "rule=#{user.rule}," +
            "country_id=#{user.countryId}," +
            "email=#{user.email}," +
            "mobile=#{user.mobile}," +
            "birthday=#{user.birthday}," +
            "name=#{user.name}," +
            "icon=#{user.icon} where user_id={user.userId}")
    public void updateUser(User user);

    //批量删除user
    @Delete("delete from user where user_id in (#{userIds})")
    public void batchDelete(String userIds);
}