package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class Application {
    private Integer id;
    private Integer schoolId;
    private Integer majorId;
    private Integer stauts;
    //申请批次
    private String batch;
    //申请类型
    private Integer applyType;
    private Integer step;

    //推荐人
    private String recommender;
    private Integer userId;
    private String name;
    private Integer gender;
    //婚姻状态
    private Integer maritalStatus;
    private Long birthday;
    private String passport;
    private String email;
    private Integer countryId;
    private String phone;
    private String file;
    private String highSchool;
    //年级人数
    private Integer gradeNumber;
    //年级排名
    private Integer gradeRanking;
    //考试类型
    private String examType;
    private Long examTime;
    private Integer totalScore;
    //个人陈述
    private String personalStatement;

    public Application(Integer userId,Integer schoolId, Integer majorId, Integer stauts, String batch, Integer applyType, Integer step) {
        this.schoolId = schoolId;
        this.majorId = majorId;
        this.stauts = stauts;
        this.batch = batch;
        this.applyType = applyType;
        this.step = step;
        this.userId=userId;
    }
}
