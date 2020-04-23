package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class School {
    private Integer schoolId;
    private String name;
    private Integer countryId;
    private Integer adminId;
    private Country country;
    private Integer schoolInfoId;
    private String logo;
    private String city;
    //学科领域
    private String subject;
    //国内排名
    private Integer nationalRanking;
    //雅思成绩
    private Float IELTS;
    //官网
    private String website;
    private SchoolInfo schoolInfo;

    public School(Integer schoolId, String name, Integer countryId, Integer adminId, String logo, String city, String subject, Integer nationalRanking, Float IELTS, String website, SchoolInfo schoolInfo,Integer schoolInfoId) {
        this.schoolId = schoolId;
        this.name = name;
        this.countryId = countryId;
        this.adminId = adminId;
        this.logo = logo;
        this.city = city;
        this.subject = subject;
        this.nationalRanking = nationalRanking;
        this.IELTS = IELTS;
        this.website = website;
        this.schoolInfo = schoolInfo;
        this.schoolInfoId=schoolInfoId;
    }

    public School(Integer schoolId, String name, Integer countryId, Integer adminId, String logo, String city) {
        this.schoolId = schoolId;
        this.name = name;
        this.countryId = countryId;
        this.adminId = adminId;
        this.logo = logo;
        this.city = city;
    }

    public School(Integer schoolId, String name, Integer countryId, Integer adminId, String logo, String city, String subject, Integer nationalRanking, Float IELTS, String website,Integer schoolInfoId) {
        this.schoolId = schoolId;
        this.name = name;
        this.countryId = countryId;
        this.adminId = adminId;
        this.logo = logo;
        this.city = city;
        this.subject = subject;
        this.nationalRanking = nationalRanking;
        this.IELTS = IELTS;
        this.website = website;
        this.schoolInfoId=schoolInfoId;
    }
}