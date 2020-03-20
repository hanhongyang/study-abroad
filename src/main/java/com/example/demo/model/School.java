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
    private String logo;
    private String city;

    public School(Integer schoolId, String name, Integer countryId, Integer adminId, String logo, String city) {
        this.schoolId = schoolId;
        this.name = name;
        this.countryId = countryId;
        this.adminId = adminId;
        this.logo = logo;
        this.city = city;
    }
}