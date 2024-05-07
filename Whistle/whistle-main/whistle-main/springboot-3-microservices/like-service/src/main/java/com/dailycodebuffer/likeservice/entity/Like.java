package com.dailycodebuffer.likeservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@IdClass(LikeId.class)
@Table(name = "likes")
public class Like {
    @Id
    @Column(name = "user_id")
    private int userId;
    @Id
    @Column(name = "post_id")
    private int postId;
}
