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
    @Results(id="userMap",value={
            @Result(property = "userId", column = "user_id", jdbcType= JdbcType.INTEGER),
            @Result(property = "password", column = "password"),
            @Result(property = "role", column = "role"),
            @Result(property = "countryId", column = "country_id"),
            @Result(property = "email", column = "email"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "name", column = "name"),
            @Result(property = "icon", column = "icon"),
            @Result(property = "uuid", column = "uuid")
    })
     List<User> getAll();

    //查询所有user携带国家信息
    @Select("select * from user")
    @Results(id="userWithCountryMap",value={
            @Result(property = "userId", column = "user_id", jdbcType= JdbcType.INTEGER),
            @Result(property = "password", column = "password"),
            @Result(property = "role", column = "role"),
            @Result(property = "countryId", column = "country_id"),
            @Result(property = "email", column = "email"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "name", column = "name"),
            @Result(property = "icon", column = "icon"),
            @Result(property = "uuid", column = "uuid"),
            @Result(property = "country", column = "country_id",one = @One(select = "com.example.demo.mapper.CountryMapper.getById"))
    })
     List<User> getAllWithCountry();
    //插入一条user数据
    @Insert("insert into user(user_id,password,role,country_id,email,mobile,birthday,name,icon) " +
            "values(#{user.userId},#{user.password}, #{user.role},#{user.countryId},#{user.email},#{user.mobile},#{user.birthday}, #{user.name},#{user.icon})")
     void insert(@Param("user") User user);

    //批量插入用户数据
     void batchSave(List<User> users);

    //批量插入随机数据
     void batchSaveRandom();

    //删除生日不为空的users
     void deleteByBirthDay();
    @Delete("delete from user where user_id=#{userId} ")
     void deleteByPrimaryKey(@Param("userId")Integer userId);

    //登录
    @Select("select * from user where email=#{email} and password=#{password}")
    @ResultMap("userMap")
     User login(@Param("email")String email,@Param("password")String password);

    //根据userId取User
    @Select("select * from user where user_id=#{userId}")
    @ResultMap("userMap")
     User selectByPrimaryKey(@Param("userId")Integer userId);

    //判断email是否已存在
    @Select("select count(*) from user where email=#{email}")
     long checkEmail(@Param("email")String email);

    //根据uuid判断githubUser是否已存在
    @Select("select count(*) from user where uuid=#{uuid} and role=3")
     long checkGithubUuid(@Param("uuid")String uuid);

    //取出githubUser
    @Select("select * from user where uuid=#{uuid} and role=3")
    @ResultMap("userMap")
     User selectGithubUserByUuid(@Param("uuid")String uuid);

    //插入一条githubUser数据
    @Insert("insert into user(role,name,icon,uuid) " +
            "values(3,#{name},#{icon},#{uuid})")
     void insertGithubUser(@Param("uuid")String uuid,@Param("name")String name,@Param("icon")String icon);

    //更新user
    @Update("update user set password=#{user.password}," +
            "role=#{user.role}," +
            "country_id=#{user.countryId}," +
            "email=#{user.email}," +
            "mobile=#{user.mobile}," +
            "birthday=#{user.birthday}," +
            "name=#{user.name}," +
            "icon=#{user.icon} where user_id=#{user.userId}")
     void updateUser(@Param("user") User user);

    //批量删除user
    @Delete("delete from user where user_id in (#{userIds})")
     void batchDelete(@Param("userIds")String userIds);
    //更新GitHub用户数据
    @Update("update user set name=#{name},icon=#{icon} where uuid=#{uuid} and role=3")
     void updateGithubUser(String name,String icon,String uuid);
    //查询所以userId
    @Select("select user_id from user")
    List<String> userIdList();
}