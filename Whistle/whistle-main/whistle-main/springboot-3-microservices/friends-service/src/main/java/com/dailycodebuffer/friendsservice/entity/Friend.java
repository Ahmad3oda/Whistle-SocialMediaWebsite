package com.dailycodebuffer.friendsservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "Friend")
@IdClass(FriendId.class)
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Friend {
    @Id
    @Column(name = "sender_id")
    private Integer senderId;

    @Id
    @Column(name = "receiver_id")
    private Integer receiverId;

    @Column(name = "request_status", length = 20)
    private String requestStatus;

}