package com.example.demo.controller;

import com.example.demo.enums.NotificationStatusEnum;
import com.example.demo.enums.NotificationTypeEnum;
import com.example.demo.model.Msg;
import com.example.demo.model.Notification;
import com.example.demo.model.User;
import com.example.demo.service.Impl.NotificationServiceImpl;
import com.example.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class NotificationController {
    @Autowired
    NotificationServiceImpl notificationService;

    @GetMapping("/NotificationCount")
    @ResponseBody
    public Msg NotificationCount(HttpSession httpSession){
        //判断是否登录
        User user=(User)httpSession.getAttribute("user");
        if(user!=null) {
            int unreadCount=notificationService.unreadCount(user.getUserId());
            //返回消息盒子里的消息列表
            List<Notification> notifications=notificationService.getMessageBox(user.getUserId());
            return Msg.success().add("unreadCount",unreadCount).add("notifications",notifications);
        }else {
            return Msg.fail();
        }
    }
    @GetMapping("/SystemNotification")
    public String systemNotification(HttpSession httpSession){
        //判断是否登录
        User user=(User)httpSession.getAttribute("user");
        if(user!=null) {
            return "SystemNotification";
               }else {

            return "SystemNotification";
        }
    }
    @PutMapping("/NotificationStatus")
    @ResponseBody
    public Msg NotificationStatus(HttpSession httpSession){
        //判断是否登录
        User user=(User)httpSession.getAttribute("user");
        if(user!=null) {
            notificationService.read(user.getUserId());
            return Msg.success();
        }else {
            return Msg.fail();
        }

    }
}
