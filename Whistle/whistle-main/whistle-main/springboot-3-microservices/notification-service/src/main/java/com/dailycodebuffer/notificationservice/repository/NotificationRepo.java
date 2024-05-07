package com.dailycodebuffer.notificationservice.repository;

import com.dailycodebuffer.notificationservice.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepo extends JpaRepository<Notification , Integer> {
    public List <Notification> findAllByReceiverId(int receiverId);
}
