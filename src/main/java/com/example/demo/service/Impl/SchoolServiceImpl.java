package com.example.demo.service.Impl;

import com.example.demo.mapper.SchoolMapper;
import com.example.demo.model.School;
import com.example.demo.service.SchoolService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    SchoolMapper schoolMapper;

    @Override
    public void save() {

        //System.out.println(schoolMapper);
        //1、插入几个学校
        // schoolMapper.insertSelective(new School(null,"test1",2,2));
        //2、批量插入学校。使用可以批量操作的sqlsession

    }
}
