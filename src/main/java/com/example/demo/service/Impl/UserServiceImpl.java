package com.example.demo.service.Impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.model.UserExample;
import com.example.demo.service.UserService;
import com.example.demo.util.RandomBirthday;
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
    public void batchSaveRandom( ){
        SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);//关闭session的自动提交

        try {
            //批量插入1000条
            for (int i=0;i<1000;i++) {
                //随机生成name
                String uid= UUID.randomUUID().toString().substring(0,5);
                //随机生成生日
                Date birthday= RandomBirthday.randomDate("1960-01-01","2010-12-31");

                userMapper.insert(new User(null,"123",2,2,i+"@qq.com",i+"",birthday,uid));
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
    public User getUser(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    /**
     * 判断email是否可用
     * @param email
     * @return ture:代表email可用  false：代表email不可用
     */
    @Override
    public boolean checkEmail(String email) {
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria=userExample.createCriteria();
        criteria.andEmailEqualTo(email);
        long count=userMapper.countByExample(userExample);
        return count==0;
    }

    /**
     * 更新user
     * @param user
     */
    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void batchDelete(List<Integer> userIdList) {
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria=userExample.createCriteria();
        //delete from user where userId in(     )
        criteria.andUserIdIn(userIdList);
        userMapper.deleteByExample(userExample);
    }

    @Override
    public void save(User user) {
        //用户Id是null，数据库自增Id，所以用insertSelective（）；
       userMapper.insertSelective(user);
    }

    @Override
    public void batchSave(List<User> users) {

    }
}
