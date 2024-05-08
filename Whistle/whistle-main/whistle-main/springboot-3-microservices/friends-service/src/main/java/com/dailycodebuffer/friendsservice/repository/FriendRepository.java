package com.dailycodebuffer.friendsservice.repository;

import com.dailycodebuffer.friendsservice.entity.Friend;
import com.dailycodebuffer.friendsservice.entity.FriendId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface FriendRepository extends JpaRepository<Friend, FriendId> {

    void deleteBySenderIdAndReceiverId(Integer senderId, Integer receiverId);
    List<Friend> findReceiverIdBySenderIdAndRequestStatus(Integer senderId, String requestStatus);
    List<Friend> findSenderIdByReceiverIdAndRequestStatus(Integer receiverId, String requestStatus);
    Friend findBySenderIdAndReceiverId(Integer senderId, Integer receiverId);
    Friend findRequestStatusBySenderIdAndReceiverId(Integer senderId, Integer receiverId);
}