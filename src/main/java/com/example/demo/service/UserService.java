package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    //插入一条user数据
    void save(User user);
    //批量插入用户数据
    void batchSave(List<User> users);
    //批量插入随机数据
    void batchSaveRandom();
    //删除生日不为空的users
    void deleteByBirthDay();
    void deleteByUserId(Integer userId);
    //查询所有users
    List<User> getAll();
    //查询所有users携带国家信息
    List<User> getAllWithCountry();
    //根据userId取User
    User getUser(Integer userId);
    //判断email是否可用
    boolean checkEmail(String email);
    //更新user
    void updateUser(User user);
    //批量删除user
    void batchDelete(List<Integer> userIdList);
    //登录
    void login();
}
