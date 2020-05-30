package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BBSNotification extends Notification {
    private Integer producer;
    private String producerName;
    private Integer outerId;
}
