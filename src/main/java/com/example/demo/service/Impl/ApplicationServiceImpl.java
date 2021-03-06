package com.example.demo.service.Impl;

import com.example.demo.mapper.ApplicationMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Application;
import com.example.demo.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    ApplicationMapper applicationMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public void saveApplyStep1(Application application) {
        applicationMapper.saveApply1(application);
    }

    @Override
    public void saveApplyStep2(Application application) {
        applicationMapper.updateApply2(application);
    }

    @Override
    public void saveApplyStep3(int id,String filePath) {
        applicationMapper.updateApply3(id,filePath);
    }

    @Override
    public void saveApplyStep4(int id,String personalStatement) {
        applicationMapper.updateApply4(id,personalStatement);
    }

    @Override
    public void saveApplyStep5(Application application) {
        applicationMapper.updateApply5(application);
    }

    @Override
    public List<List<String>> records() {
        List<String> userIdList=userMapper.userIdList();
        System.out.println(userIdList.size());
        List<List<String>> transRecords=new ArrayList<>();
        for (int i = 0; i < userIdList.size(); i++) {
            if(!applicationMapper.schoolListByUserId(Integer.parseInt(userIdList.get(i))).isEmpty()){
                transRecords.add(applicationMapper.schoolListByUserId(Integer.parseInt(userIdList.get(i))));
            }

        }
        return transRecords;

    }
}
