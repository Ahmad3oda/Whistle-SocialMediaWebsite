package com.dailycodebuffer.likeservice.controller;

import com.dailycodebuffer.likeservice.entity.Like;
import com.dailycodebuffer.likeservice.entity.LikeId;
import com.dailycodebuffer.likeservice.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @GetMapping("/getAll/{postId}")
    public int getAllLikes(@PathVariable Integer postId){
        return likeService.getAllLikesOnPost(postId);
    }

    @GetMapping("/isLiked/{userId}/{postId}")
    public ResponseEntity<Boolean> likePost(@PathVariable Integer userId, @PathVariable Integer postId) {
        return new ResponseEntity<>(likeService.isLiked(userId, postId), HttpStatus.OK);
    }

    @GetMapping("/add/{userId}/{postId}")
    public Like addLike(@PathVariable Integer userId, @PathVariable Integer postId) {
        Like like = new Like(userId,postId);
        return likeService.likePost(like);
    }

    @GetMapping("/delete/{userId}/{postId}")
    public void deleteLike(@PathVariable Integer userId, @PathVariable Integer postId) {
        LikeId like = new LikeId(userId,postId);
        likeService.deleteLike(like);
    }

    @GetMapping("/delete/{postId}")
    public void deleteLikes(@PathVariable Integer postId) {
        likeService.deleteAllLikes(postId);
    }
}
