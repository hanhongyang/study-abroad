package com.example.demo.service.Impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.util.RandomBirthday;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
@Slf4j
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

                userMapper.insert(new User(null,"123",2,2,i+"@qq.com",i+"",birthday,uid,null));
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
        return userMapper.getAll();
    }

    @Override
    public List<User> getAllWithCountry() {
        return userMapper.getAllWithCountry();
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
        long count=userMapper.checkEmail(email);
        return count==0;
    }

    /**
     * 更新user
     * @param user
     */
    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void batchDelete(List<Integer> userIdList) {
        //将userId集合拼接到字符串中，以逗号分割。
        String userId="";
        StringBuffer userIds=new StringBuffer();
        for(int i=0;i<userIdList.size();i++){
            userId=userIdList.get(i).toString()+",";
            userIds.append(userId);
        }
        //去掉最后一个逗号
        userId=userIds.toString().substring(0,userIds.length()-1);
        log.info(userId);
        //delete from user where userId in(     )
        userMapper.batchDelete(userId);
    }

    @Override
    public User login(String email,String password) {
        return userMapper.login(email,password);
    }
    @Override
    public User githubLogin(String uuid,String name,String icon) {
        //查询是否存在
        long count=userMapper.checkGithubUuid(uuid);
        //如果存在则取出User
        if(count!=0){
            User user=userMapper.selectGithubUserByUuid(uuid);
            return user;
        }else {
            //不存在则添加一个githubUser
            userMapper.insertGithubUser(uuid,name,icon);
            //再取出githubUser
            User user=userMapper.selectGithubUserByUuid(uuid);
            return user;
        }
    }
    @Override
    public void save(User user) {
        //用户Id是null，数据库自增Id，所以用insertSelective（）；
       userMapper.insert(user);
    }

    @Override
    public void batchSave(List<User> users) {

    }
}
