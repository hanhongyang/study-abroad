package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class SchoolInfo {
    private Integer id;
    private Long dateOfEstablishment;
    private Float acceptanceRate;
    private Integer tuition;
    private String educationalSystem;
    private Float crimeRate;
    private Integer averageConsumption;
    private String summary;
    private String address;
    private String email;
    private String phone;
    private Integer schoolId;
}
