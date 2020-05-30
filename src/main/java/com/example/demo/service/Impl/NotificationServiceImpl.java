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
        List<Notification> notifications=notificationMapper.getAllByConsumerOrderByGmtCreateDesc(userId);
        return notifications;
    }
    public int unreadCount(Integer userId) {
        //获取消费者的未读消息数
        return notificationMapper.getCountByConsumerAndStatusIsUnread(NotificationStatusEnum.UNREAD.getStatus());
    }
//    public NotificationDTO read(Long id, User user) {
//        Notification notification = notificationMapper.selectByPrimaryKey(id);
//        if (notification == null) {
//            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
//        }
//        if (!Objects.equals(notification.getReceiver(), user.getId())) {
//            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
//        }
//
//        notification.setStatus(NotificationStatusEnum.READ.getStatus());
//        notificationMapper.updateByPrimaryKey(notification);
//
//        NotificationDTO notificationDTO = new NotificationDTO();
//        BeanUtils.copyProperties(notification, notificationDTO);
//        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
//        return notificationDTO;
//    }
}
