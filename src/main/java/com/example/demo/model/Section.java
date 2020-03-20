package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Section {
    private Integer id;
    private Integer clickCount;
    private Integer questionCount;
    private Integer schoolId;
    private School school;

    public Section(Integer id, Integer clickCount, Integer questionCount, Integer schoolId) {
        this.id = id;
        this.clickCount = clickCount;
        this.questionCount = questionCount;
        this.schoolId = schoolId;
    }
}
