package com.dailycodebuffer.bookmarkservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bookmark")
@IdClass(BookmarkId.class)
//@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bookmark {
    @Id
    @Column(name = "user_id")
    private Integer userId;
    @Id
    @Column(name = "post_id")
    private Integer postId;

    public Bookmark(Integer userId, Integer postId) {
        this.postId = postId;
        this.userId = userId;
    }



}
