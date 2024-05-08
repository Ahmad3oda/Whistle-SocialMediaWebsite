package com.dailycodebuffer.friendsservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendRequestDetails {
    private String name;
    private int id;
}
