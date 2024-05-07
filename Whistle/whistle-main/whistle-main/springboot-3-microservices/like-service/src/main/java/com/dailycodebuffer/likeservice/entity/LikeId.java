package com.dailycodebuffer.likeservice.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
public class LikeId implements Serializable {
    private int userId;
    private int postId;
}
