package com.dailycodebuffer.friendsservice.service;

import com.dailycodebuffer.friendsservice.entity.Friend;
import com.dailycodebuffer.friendsservice.entity.FriendId;
import com.dailycodebuffer.friendsservice.entity.FriendRequestDetails;
import com.dailycodebuffer.friendsservice.repository.FriendRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FriendService {

    private final FriendRepository friendRepository;

    private static final String NOTIFICATION_ADD_URL = "http://localhost:8060/notification/add/{content}/{senderId}/{receiverId}";
    private static final String USER_ADD_URL         = "http://localhost:8060/user-management/searchById/{id}";
    private static final String USER_RESPONSE_URL    = "http://localhost:8060/user-management/searchById/{id}";



    String getUsername(Integer ownerId) {
        ResponseEntity<String> response = new RestTemplate().getForEntity(USER_RESPONSE_URL, String.class, ownerId);
        return response.getBody();
    }

    void addNotificationToFriendRequest(Friend friendRequest) {
        long senderId = friendRequest.getSenderId();
        long receiverId = friendRequest.getReceiverId();

        ResponseEntity<String> response = new RestTemplate().getForEntity(USER_ADD_URL, String.class, senderId);
        String content = response.getBody() + " send you a friend request !";

        // Building the URL with correct placeholders
        String notificationAddUrl = UriComponentsBuilder.fromHttpUrl(NOTIFICATION_ADD_URL)
                .buildAndExpand(" "+content, senderId, receiverId)
                .toUriString();
        System.out.println(notificationAddUrl);
        new RestTemplate().postForEntity(notificationAddUrl, null, String.class);
    }

    void addNotificationToAccFriendRequest(Friend friendRequest) {
        long senderId = friendRequest.getReceiverId();
        long receiverId = friendRequest.getSenderId();

        ResponseEntity<String> response = new RestTemplate().getForEntity(USER_ADD_URL, String.class, senderId);
        String content = response.getBody() + " Accepted your friend request !";
        System.out.println(response);
        System.out.println(content);

        // Building the URL with correct placeholders
        String notificationAddUrl = UriComponentsBuilder.fromHttpUrl(NOTIFICATION_ADD_URL)
                .buildAndExpand(" "+content, senderId, receiverId)
                .toUriString();
        System.out.println(notificationAddUrl);
        new RestTemplate().postForEntity(notificationAddUrl, null, String.class);
    }


    // send friend request.
    public Friend setFriendRequest(Friend friendRequest) {
        friendRequest.setRequestStatus("pending");
        addNotificationToFriendRequest(friendRequest);
        return friendRepository.save(friendRequest);
    }

    // check adding validity.
    public boolean notValidRequest(Integer senderId, Integer receiverId) {
        Friend friend = friendRepository.findBySenderIdAndReceiverId(senderId, receiverId);
        Friend friendReverse = friendRepository.findBySenderIdAndReceiverId(receiverId, senderId);
        return friend != null || friendReverse != null || Objects.equals(senderId, receiverId);
    }

    // accept friend request.
    public Friend acceptFriendRequest(Friend friendRequest) {
        friendRequest.setRequestStatus("accepted");
        addNotificationToAccFriendRequest(friendRequest);
        return friendRepository.save(friendRequest);
    }

    // check accepting validity.
    public boolean notValidAccept(Integer senderId, Integer receiverId) {
        Friend checkFriend = getBySenderIdAndReceiverId(senderId, receiverId);
        return checkFriend == null;
    }

    // reject a friend request / remove a friend
    public void removeFriend(FriendId friendId) {
        friendRepository.deleteBySenderIdAndReceiverId(friendId.getSenderId(), friendId.getReceiverId());
    }

    // get a specific friendship.
    public Friend getBySenderIdAndReceiverId(Integer senderId, Integer receiverId) {
        return friendRepository.findBySenderIdAndReceiverId(senderId, receiverId);
    }


    // get list by id.
    public  List <Integer> idsList (Integer userId, String status){
        List <Friend> userAsSender = friendRepository.findReceiverIdBySenderIdAndRequestStatus(userId, status);
        List <Friend> userAsReceiver = friendRepository.findSenderIdByReceiverIdAndRequestStatus(userId, status);

        List<Integer> receiverIds = userAsSender.stream()
                .map(Friend::getReceiverId)
                .collect(Collectors.toList());

        List<Integer> senderIds = userAsReceiver.stream()
                .map(Friend::getSenderId)
                .toList();

        receiverIds.addAll(senderIds);
        return receiverIds;
    }

    public  List <Integer> idsListAsReceiver (Integer userId, String status){
        List <Friend> userAsReceiver = friendRepository.findSenderIdByReceiverIdAndRequestStatus(userId, status);

        List<Integer> receiverIds = userAsReceiver.stream()
                .map(Friend::getReceiverId)
                .collect(Collectors.toList());

        return receiverIds;
    }


    // get friend list by id.
    public List <Integer> getFriends(Integer userId){
        return idsList(userId, "accepted");
    }

    // get requests list by id.
    public List <FriendRequestDetails> getRequests(Integer userId){
        List<Integer> res = idsListAsReceiver(userId, "pending");
        List< FriendRequestDetails > friendRequestsDetails = new ArrayList<>();
        for (Integer id : res) {
            FriendRequestDetails requestDetails = new FriendRequestDetails();
            ResponseEntity<String> response = new RestTemplate().getForEntity(USER_ADD_URL, String.class, id);
            requestDetails.setId( id );
            requestDetails.setName( response.getBody() );
            friendRequestsDetails.add(requestDetails);
        }
        return friendRequestsDetails;
    }

    // get suggestions list.
    public List <FriendRequestDetails> getSuggestions(List <Integer> friendList, Integer userId){
        Set<Integer> suggestionsSet = new HashSet<>();

        for (Integer friend : friendList) {
            List <Integer> friends = getFriends(friend);
            List <Integer> ids = new ArrayList<>();

            for (Integer friendId : friends) {
                Friend friendship = friendRepository.findRequestStatusBySenderIdAndReceiverId(userId, friendId);
                if(friendship == null){
                    friendship = friendRepository.findRequestStatusBySenderIdAndReceiverId(friendId, userId);
                }
                String status = null;
                if(friendship != null){
                    status = friendship.getRequestStatus();
                }

                if(!Objects.equals(friendId, userId) && !friendList.contains(friendId) && Objects.equals(status, null)) {
                    ids.add(friendId);
                }
            }

            suggestionsSet.addAll(ids);
        }

        List<Integer> suggestionsList = new ArrayList<>(suggestionsSet);
        Collections.shuffle(suggestionsList);

        suggestionsList = suggestionsList.subList(0, Math.min(5, suggestionsList.size()));
        List< FriendRequestDetails > friendRequestsDetails = new ArrayList<>();
        for (Integer id : suggestionsList) {
            FriendRequestDetails curr = new FriendRequestDetails();
            curr.setId(id);
            curr.setName(getUsername(id));
            friendRequestsDetails.add( curr );
        }

        return friendRequestsDetails;
    }

    // check for friendship.
    public boolean areFriends(Integer senderId, Integer receiverId) {
        Friend friend = friendRepository.findBySenderIdAndReceiverId(senderId, receiverId);
        Friend friendReverse = friendRepository.findBySenderIdAndReceiverId(receiverId, senderId);
        return friend != null || friendReverse != null;
    }
}