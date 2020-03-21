package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModify;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private Integer sectionId;
    private Integer bestAnswer;

    public Question( String title, String description, Long gmtCreate, Long gmtModify, Integer creator, Integer commentCount, Integer viewCount, Integer likeCount, String tag, Integer sectionId, Integer bestAnswer) {
        this.title = title;
        this.description = description;
        this.gmtCreate = gmtCreate;
        this.gmtModify = gmtModify;
        this.creator = creator;
        this.commentCount = commentCount;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.tag = tag;
        this.sectionId = sectionId;
        this.bestAnswer = bestAnswer;
    }
}
