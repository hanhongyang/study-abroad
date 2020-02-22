package com.example.demo.model;

import java.util.Date;

public class User {
    private Integer userId;

    private String password;

    private Integer rule;

    private Integer countryId;

    private String email;

    private Integer telNumber;

    private Date birthday;

    private String name;

    public User() {
    }

    public User(Integer userId, String password, Integer rule, Integer countryId, String email, Integer telNumber, Date birthday, String name) {
        this.userId = userId;
        this.password = password;
        this.rule = rule;
        this.countryId = countryId;
        this.email = email;
        this.telNumber = telNumber;
        this.birthday = birthday;
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getRule() {
        return rule;
    }

    public void setRule(Integer rule) {
        this.rule = rule;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(Integer telNumber) {
        this.telNumber = telNumber;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}