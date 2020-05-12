package com.example.demo.service.Impl;

import com.example.demo.model.Application;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class ApplicationServiceImplTest {
    @Autowired
    ApplicationServiceImpl applicationService;
    @Test
    void saveApply1() {
        Application application=new Application(1,1,1,1,"1",1,1);
        applicationService.saveApplyStep1(application);
        //获取刚插入的申请的id
        int id=application.getId();
        System.out.println(id);
    }
}