package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class Country {
    private Integer countryId;

    private String name;

    private String code;
    private String flag;

}