package com.example.demo.model;

public class Country {
    private Integer countryId;

    private String name;

    private String code;

    public Country() {
    }

    public Country(Integer countryId, String name, String code) {
        this.countryId = countryId;
        this.name = name;
        this.code = code;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
}