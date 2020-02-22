package com.example.demo.model;

public class School {
    private Integer schoolId;

    private String name;

    private Integer countryId;

    private Integer adminId;
    private Country country;

    public School() {
    }

    public School(Integer schoolId, String name, Integer countryId, Integer adminId) {
        this.schoolId = schoolId;
        this.name = name;
        this.countryId = countryId;
        this.adminId = adminId;

    }

    public School(Integer schoolId, String name, Integer countryId, Integer adminId, Country country) {
        this.schoolId = schoolId;
        this.name = name;
        this.countryId = countryId;
        this.adminId = adminId;
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }
}