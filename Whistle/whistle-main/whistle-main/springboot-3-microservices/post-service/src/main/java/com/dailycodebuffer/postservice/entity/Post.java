package com.dailycodebuffer.postservice.entity;

import jakarta.persistence.*;
import jakarta.ws.rs.DefaultValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.*;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "owner_id")
    private Integer ownerId;

    @Column(name = "content")
    private String content;

    @Column(name = "post_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date postDate;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "username")
    private String username;

    @Column(name = "comments_number", columnDefinition = "int default 0")
    private Integer commentsNumber;


    @Column(name = "likes_number" , columnDefinition = "int default 0")
    private Integer likesNumber;

}