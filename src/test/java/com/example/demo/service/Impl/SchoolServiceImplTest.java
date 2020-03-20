package com.example.demo.service.Impl;

import com.example.demo.service.SchoolService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class SchoolServiceImplTest {
@Autowired
    SchoolService schoolService;
    @Test
    void getAll() {
        System.out.println(schoolService.getAll());
    }

    @Test
    void getById() {
        System.out.println(schoolService.getById(1));
    }
}