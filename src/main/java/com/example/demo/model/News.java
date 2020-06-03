package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class News implements Serializable {
    private int id;
    private String title;
    private String description;
    private String createTime;
}
