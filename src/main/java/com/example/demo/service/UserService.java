package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    //插入一条user数据
    void save();
    //批量插入数据
    void batchSave();
    //删除生日不为空的users
    void deleteByBirthDay();
    void deleteByUserId(Integer userId);
    //查询所有users
    List<User> getAll();
}
