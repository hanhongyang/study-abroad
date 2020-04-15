package com.example.demo.service.Impl;

import com.example.demo.mapper.CountryMapper;
import com.example.demo.model.Country;
import com.example.demo.model.User;
import com.example.demo.service.CountryService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;//引入bean
    @Autowired
    CountryMapper countryMapper;

    /**
     * 把REGIONS文件内国家数据传入数据库
     */
    @Override
    public void batchSave() {

        SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);//关闭session的自动提交

        try{
            //获取REGIONS文件
            File file = new File("C:\\Users\\11437\\IdeaProjects\\demo\\src\\main\\resources\\static\\REGIONS.txt");
            //构造一个BufferedReader类来读取文件
            BufferedReader br = new BufferedReader(new FileReader(file));
            //s保存每一行的数据
            String s = null;
            //i保存国家数量
            int i=0;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                //当读到code=region时
                if(s.contains("=")){
                    //截取国家code和region
                    String code=s.substring(0,s.indexOf("="));
                    String region=s.substring(s.indexOf("=")+1);
                    //存入数据库

                    try{
                        countryMapper.insert2(new Country(null,region,code,null));
                        if (i % 20 == 0 || i == 1000) {
                            //手动每1000个一提交，提交后无法回滚
                            session.commit();
                            session.clearCache();//注意，如果没有这个动作，可能会导致内存崩溃。
                        }
                    }catch (Exception e) {
                    //没有提交的数据可以回滚
                    session.rollback();
                } finally{
                    session.close();
                }
                    //国家数加一
                    i++;
                    System.out.println(i);
                }else if(s.contains("STOP")){
                    break;
                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    @Override
    public List<Country> getAll() {
        return countryMapper.getAll();
    }

    @Override
    public Country getById(Integer countryId) {
        return countryMapper.getById(countryId);
    }

    @Override
    public List<Country> popCountries() {
        return countryMapper.popCountries();
    }


}
