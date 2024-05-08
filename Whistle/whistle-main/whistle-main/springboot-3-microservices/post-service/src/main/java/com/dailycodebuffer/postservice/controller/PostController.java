package com.dailycodebuffer.postservice.controller;

import com.dailycodebuffer.postservice.entity.Post;
import com.dailycodebuffer.postservice.entity.CompletePost;
import com.dailycodebuffer.postservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:8088"})
@RequestMapping("/post")
public class PostController {

    @Autowired
    private final PostService postService;

    // add post.

    @GetMapping("/post/{ownerId}/{content}/{imagePath}")
    public ResponseEntity<Post> addPost(@PathVariable int ownerId, @PathVariable String content, @PathVariable String imagePath) {
        Post post = new Post();

        post.setOwnerId(ownerId);
        post.setContent(content);
        post.setImagePath(imagePath);
        post.setCommentsNumber(0);
//        post.setUsername(postService.getById(ownerId).getUsername());
        if(postService.checkPostValidity(post)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Post savedPost = postService.createPost(post);
        return ResponseEntity.ok(savedPost);
    }

    // edit post.
    @GetMapping("/post/{postId}")
    public ResponseEntity<Post> editPost(@PathVariable Integer postId, @RequestBody Post post) {

        if(postService.checkPostValidity(post)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Post updatedPost = postService.editPost(postId, post);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    // delete post.
    @GetMapping("/post/delete/{postId}")
    public ResponseEntity<Post> deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // get posts of a single user by id.
    @GetMapping("/self/{userId}")
    public ResponseEntity<List<CompletePost>> getSelfPosts(@PathVariable Integer userId) {
        List<CompletePost> posts = postService.getPostsByOwnerIdRetCompletePost(userId);
        return ResponseEntity.ok(posts);
    }

    // get posts for timeline.
    @GetMapping("/friends/{userId}")
    public ResponseEntity<List<CompletePost>> getFriendsPosts(@PathVariable Integer userId) {
        List<CompletePost> posts = postService.getFriendsPosts(userId);
        return ResponseEntity.ok(posts);
    }

    // get bookmarks.
    @GetMapping("/bookmarks/{userId}")
    public ResponseEntity<List<CompletePost>> getBookmarksPosts(@PathVariable Integer userId) {
        List <Integer> postsIds = postService.getBookmarkIds(userId);
        return ResponseEntity.ok(postService.getBookmarks(postsIds , userId));
    }

    @GetMapping("/getOwnerId/{postId}")
    public Integer getOwnerId(@PathVariable Integer postId) {
        return postService.getOwnerIdByPostId(postId);
    }

}
