package com.dailycodebuffer.commentservice.service;

import com.dailycodebuffer.commentservice.entity.Comment;
import com.dailycodebuffer.commentservice.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Component
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private static final String NOTIFICATION_ADD_URL = "http://localhost:8060/notification/add/{content}/{senderId}/{receiverId}";
    private static final String USER_ADD_URL = "http://localhost:8060/user-management/searchById/{id}";

    private final CommentRepository commentRepository;

    void addNotification(Comment comment) {
        long senderId = comment.getCommenterId();
        long receiverId = comment.getOwnerId();

        ResponseEntity<String> response = new RestTemplate().getForEntity(USER_ADD_URL, String.class, senderId);
        String content = response.getBody() + " commented on your post!";
        System.out.println(response);
        System.out.println(content);

        // Building the URL with correct placeholders
        String notificationAddUrl = UriComponentsBuilder.fromHttpUrl(NOTIFICATION_ADD_URL)
                .buildAndExpand(" "+content, senderId, receiverId)
                .toUriString();
        System.out.println(notificationAddUrl);
        new RestTemplate().postForEntity(notificationAddUrl, null, String.class);
    }

    // add a comment.
    public Comment addComment(Long ownerId, Long commenterId, Long postId, String content) {
        Comment comment = new Comment(ownerId, commenterId, postId, content);
        addNotification(comment);
        return commentRepository.save(comment);
    }

    // delete a comment by id.
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    // get the comments by post id.
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    // get the number of comments on a post by post id.
    public Long countCommentsByPostId(Long postId) {
        return commentRepository.countByPostId(postId);
    }

    public void deleteByPostId(Long postId) {
        commentRepository.deleteByPostId(postId);
    }

}