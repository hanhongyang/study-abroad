package com.example.demo.service;

import com.example.demo.model.Application;

import java.util.List;

public interface ApplicationService {

    //保存申请第一步
    void saveApplyStep1(Application application);
    //保存申请第二步
    void saveApplyStep2(Application application);
    //保存申请第三步
    void saveApplyStep3(int id,String filePath);
    //保存申请第四步
    void saveApplyStep4(Application application);
    //保存申请第五步
    void saveApplyStep5(Application application);
    //留学推荐数据
    List<List<String>> records();
}
