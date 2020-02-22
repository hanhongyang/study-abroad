package com.example.demo.test;

import com.example.demo.DemoApplication;
import com.example.demo.mapper.SchoolMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 测试dao层
 */

@RunWith(JUnit4.class)
public class MapperTest {
    /**
     * 测试SchoolMapper
     */
    @Autowired
    SchoolMapper schoolMapper;
    @Test
    public void testCRUD(){
        System.out.println(schoolMapper);
    }
}