package com.example.demo.model;

import lombok.Data;

@Data
public class Major {
    private Integer id;
    //所属学院
    private String college;
    //所属学校Id
    private  Integer schoolId;
    //学科
    private String subject;
    private String name;
}
