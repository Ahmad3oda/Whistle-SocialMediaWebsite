package com.dailycodebuffer.friendsservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FriendId implements Serializable {
    private Integer senderId;
    private Integer receiverId;
}