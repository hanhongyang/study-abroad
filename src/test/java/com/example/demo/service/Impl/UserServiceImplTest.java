package com.example.demo.service.Impl;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class UserServiceImplTest {
@Autowired
UserService userService;
    @Test
    void batchSaveRandom() {
        userService.batchSaveRandom();
    }

    @Test
    void insert() {
    }
    @Test
    void getUser() {
        int userId=5003;
        User user=userService.getUser(userId);
        System.out.println(user.getBirthday());
    }
    @Test
    void getAll(){
        System.out.println(userService.getAll());
    }
    @Test
    void batchDelete(){
        List<Integer> userIdList=new ArrayList<>();
        userIdList.add(100);
        userIdList.add(200);
        userService.batchDelete(userIdList);
    }

}