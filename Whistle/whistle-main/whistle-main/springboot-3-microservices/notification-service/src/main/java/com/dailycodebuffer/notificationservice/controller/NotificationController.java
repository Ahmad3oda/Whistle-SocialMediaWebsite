package com.dailycodebuffer.notificationservice.controller;

import com.dailycodebuffer.notificationservice.model.Notification;
import com.dailycodebuffer.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/add/{content}/{senderId}/{receiverId}")
    public Notification Notify(@PathVariable String content, @PathVariable Integer senderId, @PathVariable Integer receiverId){
        Notification notification = new Notification();
        notification.setContent(content);
        notification.setSenderId(senderId);
        notification.setReceiverId(receiverId);
        return notificationService.add(notification);
    }
    @GetMapping("/user/{receiverId}")
    public List <Notification> getNotifications(@PathVariable int receiverId){
        return notificationService.getAll(receiverId);
    }
}
