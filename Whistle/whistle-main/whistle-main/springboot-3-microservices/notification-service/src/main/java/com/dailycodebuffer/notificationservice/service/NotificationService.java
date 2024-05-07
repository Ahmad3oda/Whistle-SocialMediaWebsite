package com.dailycodebuffer.notificationservice.service;

import com.dailycodebuffer.notificationservice.model.Notification;
import com.dailycodebuffer.notificationservice.repository.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepo notificationRepo;

    public Notification add(Notification notification){
        return notificationRepo.save(notification);
    }

    public List <Notification> getAll(int receiverId){
        return notificationRepo.findAllByReceiverId(receiverId);
    }
}
