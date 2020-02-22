package com.example.demo.service.Impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.School;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;//引入bean
@Autowired
    UserMapper userMapper;
    @Override
    public void batchSave( ){
        SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);//关闭session的自动提交

        try {
            //批量插入1000条
            for (int i=0;i<1000;i++) {
                //随机生成name
                String uid= UUID.randomUUID().toString().substring(0,5);
                userMapper.insert(new User(null,"123",2,2,i+"@qq.com",i,new Date(),uid));
                if (i % 100 == 0 || i == 1000) {
                    //手动每1000个一提交，提交后无法回滚
                    session.commit();
                    session.clearCache();//注意，如果没有这个动作，可能会导致内存崩溃。
                }

            }
        }catch (Exception e) {
            //没有提交的数据可以回滚
            session.rollback();
        } finally{
            session.close();
        }
    }

    @Override
    public void deleteByBirthDay() {

    }

    @Override
    public void deleteByUserId(Integer userId) {

        userMapper.deleteByPrimaryKey(userId);
    }


    @Override
    public List<User> getAll() {
        return userMapper.selectByExample(null);
    }

    @Override
    public void save() {

        System.out.println(userMapper);
    }
}
