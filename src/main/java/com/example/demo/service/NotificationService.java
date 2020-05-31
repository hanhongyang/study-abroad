package com.example.demo.service;

import com.example.demo.model.Notification;

import java.util.List;

public interface NotificationService {
    //获取用户消息
    //List<Notification> getUserNotifications(Integer userId);
    //消息未读数
    int unreadCount(Integer userId);
}
