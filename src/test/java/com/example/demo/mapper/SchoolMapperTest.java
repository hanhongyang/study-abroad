package com.example.demo.mapper;

import com.example.demo.DemoApplication;
import com.example.demo.model.School;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SchoolMapperTest {
    /**
     * 测试SchoolMapper
     */
    @Autowired
    SchoolMapper schoolMapper;
    @Test
    public void testCRUD(){
        System.out.println(schoolMapper);
        //1、插入几个学校
        //schoolMapper.insertSelective(new School(null,"test1",2,2));
    }

}