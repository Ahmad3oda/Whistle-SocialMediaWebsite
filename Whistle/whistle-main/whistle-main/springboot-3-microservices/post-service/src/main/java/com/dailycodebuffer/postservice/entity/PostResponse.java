package com.dailycodebuffer.postservice.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private Integer postId;
    private Integer ownerId;
    private String content;
    private Date postDate;
    private String imagePath;
    private Integer commentsNumber;
    private Integer likesNumber;
    private String username;

}
