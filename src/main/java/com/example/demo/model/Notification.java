package com.example.demo.model;

import com.example.demo.enums.NotificationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public  class Notification{
    public Integer id;
    public Integer type;
    public Integer consumer;
    public Long gmtCreate;
    public Integer status;
    public String outerTitle;
    public String content;
    private Integer producer;
    private String producerName;
    private Integer outerId;
    public Notification() {
    }

    public Notification(Integer type, Integer consumer, Long gmtCreate, Integer status, String outerTitle, String content, Integer producer, String producerName, Integer outerId) {
        this.type = type;
        this.consumer = consumer;
        this.gmtCreate = gmtCreate;
        this.status = status;
        this.outerTitle = outerTitle;
        this.content = content;
        this.producer = producer;
        this.producerName = producerName;
        this.outerId = outerId;
    }

    public Notification(Integer type, Integer consumer, Long gmtCreate, Integer status, String outerTitle, String content) {
        this.type = type;
        this.consumer = consumer;
        this.gmtCreate = gmtCreate;
        this.status = status;
        this.outerTitle = outerTitle;
        this.content = content;
    }
}
