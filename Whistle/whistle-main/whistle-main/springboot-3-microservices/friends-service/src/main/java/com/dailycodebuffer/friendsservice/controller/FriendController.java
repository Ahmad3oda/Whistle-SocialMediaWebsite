package com.dailycodebuffer.friendsservice.controller;

import com.dailycodebuffer.friendsservice.entity.Friend;
import com.dailycodebuffer.friendsservice.entity.FriendId;
import com.dailycodebuffer.friendsservice.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:8088"})
@RequestMapping("/friends")
public class FriendController {
    @Autowired
    private FriendService friendService;

    // send a friend request
    @PostMapping("/request/{senderID}/{receiverID}")
    public ResponseEntity<Friend> addFriend(@PathVariable Integer senderID, @PathVariable Integer receiverID) {

        if(friendService.notValidRequest(senderID, receiverID)){
            return ResponseEntity.badRequest().build();
        }

        Friend friend = new Friend();
        friend.setSenderId(senderID);
        friend.setReceiverId(receiverID);

        return ResponseEntity.ok(friendService.setFriendRequest(friend));
    }

    // accept a friend request
    @GetMapping("/request/{senderID}/{receiverID}")
    public ResponseEntity<Friend> acceptFriend(@PathVariable Integer senderID, @PathVariable Integer receiverID) {

        if(friendService.notValidAccept(senderID, receiverID)){
            return ResponseEntity.badRequest().build();
        }

        Friend friend = new Friend();
        friend.setSenderId(senderID);
        friend.setReceiverId(receiverID);

        return ResponseEntity.ok(friendService.acceptFriendRequest(friend));
    }

    // reject a friend request / remove a friend
    @GetMapping("/request/delete/{senderID}/{receiverID}")
    public ResponseEntity<?> removeFriend(@PathVariable Integer senderID, @PathVariable Integer receiverID) {

        FriendId friendId = new FriendId();

        friendId.setSenderId(senderID);
        friendId.setReceiverId(receiverID);
        friendService.removeFriend(friendId);

        friendId.setSenderId(receiverID);
        friendId.setReceiverId(senderID);
        friendService.removeFriend(friendId);

        return ResponseEntity.ok().build();
    }

    // get the friend list of a user by id.
    @GetMapping("/friends/{userId}")
    public List<Integer> getFriendList(@PathVariable Integer userId) {
        return friendService.getFriends(userId);
    }

    // get the requests list of a user by id.
        @GetMapping("/requests/{userId}")
        public List<Integer> getRequestList(@PathVariable Integer userId) {
            return friendService.getRequests(userId);
        }

    // get the suggestions list by id.
    @GetMapping("/suggestions/{userId}")
    public List<Integer> getSuggestionList(@PathVariable Integer userId) {
        return friendService.getSuggestions(friendService.getFriends(userId), userId);
    }

    // check for friendship.
    @GetMapping("/isFriend/{senderId}/{receiverId}")
    public ResponseEntity<Boolean> isFriend(@PathVariable Integer senderId, @PathVariable Integer receiverId) {
        return ResponseEntity.ok(friendService.areFriends(senderId, receiverId));
    }
}
