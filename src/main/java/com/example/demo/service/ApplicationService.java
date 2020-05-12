package com.example.demo.service;

import com.example.demo.model.Application;

public interface ApplicationService {

    //保存申请第一步
    void saveApplyStep1(Application application);
    //保存申请第二步
    void saveApplyStep2();
    //保存申请第三步
    void saveApplyStep3();
    //保存申请第四步
    void saveApplyStep4();
}
