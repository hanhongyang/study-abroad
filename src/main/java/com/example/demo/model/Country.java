package com.example.demo.model;

public class Country {
    private Integer countryId;

    private String name;

    public Country(Integer countryId, String name) {
        this.countryId = countryId;
        this.name = name;
    }

    public Country() {
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
}