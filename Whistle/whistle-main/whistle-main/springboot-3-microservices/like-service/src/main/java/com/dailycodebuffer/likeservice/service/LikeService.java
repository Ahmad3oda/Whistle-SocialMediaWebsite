package com.dailycodebuffer.likeservice.service;

import com.dailycodebuffer.likeservice.entity.Like;
import com.dailycodebuffer.likeservice.entity.LikeId;
import com.dailycodebuffer.likeservice.repository.likesRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class LikeService {

    @Autowired
    private likesRepository likeRepo;

    private static final String POST_OWNER_URL = "http://localhost:8060/post/getOwnerId/{postId}";
    private static final String NOTIFICATION_ADD_URL = "http://localhost:8060/notification/add/{content}/{senderId}/{receiverId}";
    private static String NOTIFICATION_ADD_URL2 = "http://localhost:8060/notification/add/";
    private static final String USER_ADD_URL = "http://localhost:8060/user-management/searchById/{id}";

    public int getAllLikesOnPost(int postId){
        return likeRepo.countByPostId(postId);
    }

    public Boolean isLiked(int userId, int postId){
        return likeRepo.existsByUserIdAndPostId(userId, postId);
    }

    Integer getReceiverId(Integer postId) {
        ResponseEntity<Integer> response = new RestTemplate().getForEntity(POST_OWNER_URL, Integer.class, postId);
        return response.getBody();
    }
//    void addNotification2(Like like){
//        Integer senderId = like.getUserId();
//        Integer receiverId = getReceiverId(like.getPostId());
//
//        ResponseEntity<String> response = new RestTemplate().getForEntity(USER_ADD_URL, String.class, senderId);
//        String content = response.getBody() + " liked your post!";
//        System.out.println(response);
//        System.out.println(content);
//        NOTIFICATION_ADD_URL2 = NOTIFICATION_ADD_URL2 + content + "/" + String.valueOf(senderId) + "/" + String.valueOf(receiverId);
//        ResponseEntity<Integer> response2 = new RestTemplate().getForEntity(NOTIFICATION_ADD_URL2, Integer.class);
//    }

    void addNotification(Like like) {
        Integer senderId = like.getUserId();
        Integer receiverId = getReceiverId(like.getPostId());

        ResponseEntity<String> response = new RestTemplate().getForEntity(USER_ADD_URL, String.class, senderId);
        String content = response.getBody() + " liked your post!";
        System.out.println(response);
        System.out.println(content);

        // Building the URL with correct placeholders
        String notificationAddUrl = UriComponentsBuilder.fromHttpUrl(NOTIFICATION_ADD_URL)
                .buildAndExpand(" "+content, senderId, receiverId)
                .toUriString();
        System.out.println(notificationAddUrl);
        new RestTemplate().postForEntity(notificationAddUrl, null, String.class);
    }
    public Like likePost(Like like){
        addNotification(like);
        return likeRepo.save(like);
    }

    public void deleteLike(LikeId like){
        likeRepo.deleteById(like);
    }

    public void deleteAllLikes(int postId){
        likeRepo.deleteAllByPostId(postId);
    }

}
