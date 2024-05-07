package com.dailycodebuffer.bookmarkservice.controller;

import com.dailycodebuffer.bookmarkservice.entity.Bookmark;
import com.dailycodebuffer.bookmarkservice.entity.BookmarkId;
import com.dailycodebuffer.bookmarkservice.service.BookmarkServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bookmark")
@CrossOrigin
public class BookmarkController {
    @Autowired
    private BookmarkServices bookmarkServices;

    @GetMapping("/getAllBookmarks/{userId}")
    public List<Integer>getBookmarks(@PathVariable Integer userId){
        List<Bookmark>bookmarks = bookmarkServices.getBookmarks(userId);
        List<Integer> postIds = new ArrayList<Integer>();
        for (Bookmark bookmark : bookmarks) {
            postIds.add(bookmark.getPostId());
        }
        return postIds;
    }

    @PostMapping("/addBookmark")
    public void addBookmark(@RequestBody Bookmark bookmark){
        bookmarkServices.addBookmark(bookmark);
    }

    @GetMapping("/deleteBookmark/{userId}/{postId}")
    public boolean deleteBookmark(@PathVariable Integer userId, @PathVariable Integer postId){
        bookmarkServices.deleteBookmark(userId,postId);
        return true;
    }

    @GetMapping("/isBookmarked/{userId}/{postId}")
    public boolean isBookmarked(@PathVariable Integer userId,@PathVariable Integer postId){
        return bookmarkServices.isBookmarked(userId, postId);
    }


    @GetMapping("/deleteAllByPostId/{postId}")
    public void deleteAllByPostId(@PathVariable Integer postId){
         bookmarkServices.deleteBookmarksOfPost(postId);
    }

    // are you here ?
    @GetMapping("/getBookmarksData/{userId}")
    public ResponseEntity<List<Integer>> getBookmarksIds(@PathVariable Integer userId){
        List<Bookmark>bookmarks = bookmarkServices.getBookmarks(userId);
        List<Integer> postIds = new ArrayList<>();
        for (Bookmark bookmark : bookmarks) {
            postIds.add(bookmark.getPostId());
        }
        return ResponseEntity.ok(postIds);
    }

}
