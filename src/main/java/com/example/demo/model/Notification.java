package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public abstract class Notification{
    public Integer id;
    public Integer type;
    public Integer consumer;
    public Long gmtCreate;
    public Integer status;
    public String outerTitle;
    public String content;
    public Notification() {
    }

}
