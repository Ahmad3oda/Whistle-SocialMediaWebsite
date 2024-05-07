package com.dailycodebuffer.commentservice.entity;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "commenter_id")
    private Long commenterId;

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    // Constructors, getters, and setters
    // Constructor(s)
    public Comment() {}

    public Comment(Long ownerId, Long commenterId, Long postId, String content) {
        this.ownerId = ownerId;
        this.commenterId = commenterId;
        this.postId = postId;
        this.content = content;
    }

    // Getters and setters
    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getCommenterId() {
        return commenterId;
    }

    public void setCommenterId(Long commenterId) {
        this.commenterId = commenterId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}