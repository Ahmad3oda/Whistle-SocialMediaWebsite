package com.dailycodebuffer.commentservice.controller;

import com.dailycodebuffer.commentservice.entity.Comment;
import com.dailycodebuffer.commentservice.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:8088"})
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // add a comment in the database
    @PostMapping("/add")
    public Comment addComment(@RequestBody Comment commentRequest) {
        Long ownerId = commentRequest.getOwnerId();
        Long postId = commentRequest.getPostId();
        Long commenterId = commentRequest.getCommenterId();
        String content = commentRequest.getContent();
        return commentService.addComment(ownerId, commenterId, postId, content);
    }

    // get the number of comments for displaying them in the post
    @GetMapping("/count/{postId}")
    public Long getNumberOfComments(@PathVariable Long postId) {
        return commentService.countCommentsByPostId(postId);
    }

    // get the comments for displaying them in the post's comment section
    @GetMapping("/comments/{postId}")
    public List<Comment> getCommentsByPostId(@PathVariable Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    // delete a comment by id
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }

    // delete all comments within a post
    @DeleteMapping("/deleteAll/{postId}")
    public ResponseEntity<?> deleteAllComment(@PathVariable Long postId) {
        commentService.deleteByPostId(postId);
        return ResponseEntity.ok().build();
    }
}
