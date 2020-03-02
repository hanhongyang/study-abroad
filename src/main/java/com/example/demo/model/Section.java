package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Section {
    private Integer id;
    private String name;
    private Integer clickCount;
    private Integer questionCount;
    private Integer adminId;

}
