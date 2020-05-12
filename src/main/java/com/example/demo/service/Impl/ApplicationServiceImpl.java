package com.example.demo.service.Impl;

import com.example.demo.mapper.ApplicationMapper;
import com.example.demo.model.Application;
import com.example.demo.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    ApplicationMapper applicationMapper;
    @Override
    public void saveApplyStep1(Application application) {
        applicationMapper.saveApply1(application);

    }

    @Override
    public void saveApplyStep2() {

    }

    @Override
    public void saveApplyStep3() {

    }

    @Override
    public void saveApplyStep4() {

    }
}
