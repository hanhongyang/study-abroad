package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Comment {
    private Integer id;
    private Integer parentId;
    private Integer type;
    private Integer commentator;
    private Long gmtCreate;
    private Long gmtModify;
    private Integer likeCount;
    private Integer commentCount;
    private String content;
    private User user;

    public Comment(Integer id, Integer parentId, Integer type, Integer commentator, Long gmtCreate, Long gmtModify, Integer likeCount, Integer commentCount, String content) {
        this.id = id;
        this.parentId = parentId;
        this.type = type;
        this.commentator = commentator;
        this.gmtCreate = gmtCreate;
        this.gmtModify = gmtModify;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.content = content;
    }
}
