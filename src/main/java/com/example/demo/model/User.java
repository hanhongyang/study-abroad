package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Pattern;
import java.util.Date;
@AllArgsConstructor
@Data
public class User {
    private Integer userId;

    private String password;

    private Integer rule;

    private Integer countryId;

    private String email;

    private String mobile;

    private Date birthday;

    private String name;

    private String icon;
}