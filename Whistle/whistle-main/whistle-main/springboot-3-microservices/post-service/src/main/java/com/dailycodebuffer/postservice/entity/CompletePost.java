package com.dailycodebuffer.postservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompletePost {
    private Integer postId;
    private Integer ownerId;
    private String content;
    private Date postDate;
    private String imagePath;
    private String username;
    private Integer commentsNumber;
    private Integer likesNumber;
    private boolean isLiked;
    private boolean isBookMarked;
}
