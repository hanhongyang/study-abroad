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
    private Integer sectionId;
    private String logo;
    private String city;
}