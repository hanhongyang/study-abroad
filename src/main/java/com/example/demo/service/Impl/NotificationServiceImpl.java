package com.example.demo.service.Impl;

import com.example.demo.enums.NotificationStatusEnum;
import com.example.demo.mapper.NotificationMapper;
import com.example.demo.model.Notification;
import com.example.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;

    public List<Notification> getUserNotifications(Integer userId){
        //获取消费者的消息列表按照创建时间倒序排列
        List<Notification> recommendNotifications=notificationMapper.getAllRecommendNotificationByConsumerOrderByGmtCreateDesc(userId);
        return recommendNotifications;
    }
    @Override
    public int unreadCount(Integer userId) {
        //获取消费者的未读消息数
        return notificationMapper.getCountByConsumerAndStatusIsUnread(NotificationStatusEnum.UNREAD.getStatus(),userId);
    }
    public void createNotify(Notification recommendNotification){
        notificationMapper.insertRecommendNotification(recommendNotification);
    }

    public List<Notification> getMessageBox(Integer userId) {
        //获取消费者的消息列表按照创建时间倒序排列的前四条信息
        List<Notification> recommendNotifications=notificationMapper.getAllRecommendNotificationByConsumerOrderByGmtCreateDescLimit4(NotificationStatusEnum.UNREAD.getStatus(),userId);
        return recommendNotifications;
    }
    public void read(int userId) {
       notificationMapper.updateNotificationStatusByConsumer(userId);
    }
}
