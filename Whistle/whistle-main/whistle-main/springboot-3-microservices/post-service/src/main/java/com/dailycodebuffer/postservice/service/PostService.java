package com.dailycodebuffer.postservice.service;

import com.dailycodebuffer.postservice.entity.Post;
import com.dailycodebuffer.postservice.entity.CompletePost;
import com.dailycodebuffer.postservice.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private static final String COMMENT_DELETE_URL = "http://localhost:8060/comment/deleteAll/{postId}";
    private static final String COMMENT_COUNT_URL = "http://localhost:8060/comment/count/{postId}";
    private static final String FRIEND_IDS_URL = "http://localhost:8060/friends/friends/{userId}";
    private static final String BOOKMARK_IDS_URL = "http://localhost:8060/bookmark/getBookmarksData/{userId}";
    private static final String BOOKMARK_DELETE_URL = "http://localhost:8060/bookmark/deleteAllByPostId/{postId}";
    private static final String LIKES_COUNT_URL = "http://localhost:8060/like/getAll/{postId}";
    private static final String LIKES_DELETE_URL = "http://localhost:8060/like/delete/{postId}";
    private static final String USER_RESPONSE_URL = "http://localhost:8060/user-management/searchById/{id}";
    private static final String LIKES_IS_LIKES_URL = "http://localhost:8060/like/isLiked/{userId}/{postId}";
    private static final String BOOKMARK_IS_BOOKMARKED_URL = "http://localhost:8060/bookmark/isBookmarked/{userId}/{postId}";

    public Boolean isLiked(int postId,int userId) {
        ResponseEntity<Boolean> response = new RestTemplate().getForEntity(LIKES_IS_LIKES_URL, Boolean.class, userId, postId);
        Boolean isLiked = response.getBody();
        System.out.println(response);
        //System.out.println("Is liked: " + isLiked);
        return isLiked;
    }
    public Boolean isBookMarked(int postId,int userId) {
        ResponseEntity<Boolean> response = new RestTemplate().getForEntity(BOOKMARK_IS_BOOKMARKED_URL, Boolean.class, userId, postId);
        Boolean isLiked = response.getBody();
        System.out.println(response);
        //System.out.println("Is liked: " + isLiked);
        return isLiked;
    }

    // check post validity.
    public Boolean checkPostValidity(Post post) {
        return post.getOwnerId() == null || (post.getContent() == null && post.getImagePath() == null);
    }

    // add post.
    public Post createPost(Post post) {
        post.setPostDate(new Date());
        return postRepository.save(post);
    }

    // edit post.
    public Post editPost(Integer postId, Post updatedPost) {
        Post existingPost = postRepository.findById(postId).orElse(null);
        if (existingPost == null) {
            return null;
        }

        existingPost.setContent(updatedPost.getContent());
        existingPost.setImagePath(updatedPost.getImagePath());
        existingPost.setPostDate(new Date());
        return postRepository.save(existingPost);
    }

    // delete post.
    public void deletePost(Integer postId) {
        if (postRepository.existsById(postId)) {
            deleteComments(postId);
            deleteLikes(postId);
            deleteBookmarks(postId);
            postRepository.deleteById(postId);
        }
    }

    void deleteComments(Integer postId) {
        ResponseEntity<Void> response = new RestTemplate().exchange(COMMENT_DELETE_URL, HttpMethod.GET, null, Void.class, postId);
        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Comments deleted successfully");
        } else {
            System.err.println("Failed to delete comments. Status code: " + response.getStatusCode());
        }
    }

    void deleteLikes(Integer postId) {
        ResponseEntity<Void> response = new RestTemplate().exchange(LIKES_DELETE_URL, HttpMethod.GET, null, Void.class, postId);
        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Comments deleted successfully");
        } else {
            System.err.println("Failed to delete comments. Status code: " + response.getStatusCode());
        }
    }

    void deleteBookmarks(Integer postId) {
        ResponseEntity<Void> response = new RestTemplate().exchange(BOOKMARK_DELETE_URL, HttpMethod.GET, null, Void.class, postId);
        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Comments deleted successfully");
        } else {
            System.err.println("Failed to delete comments. Status code: " + response.getStatusCode());
        }
    }

    // display posts.

    public Post getById(Integer postId) {
        Post post = postRepository.findById(postId).orElse(null);
        assert post != null;
        System.out.println(countComments(postId)+"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        post.setCommentsNumber(countComments(postId));
        post.setLikesNumber(countLikes(postId));
        post.setUsername(getUsername(post.getOwnerId()));
        return post;
    }

    public List<Post> getPostsByOwnerId(Integer ownerId) {
        List<Post> posts = postRepository.findByOwnerId(ownerId);
        for (Post post : posts) {
            Post updated = getById(post.getPostId());
            post.setCommentsNumber(updated.getCommentsNumber());
            post.setLikesNumber(updated.getLikesNumber());
            post.setUsername(getUsername(post.getOwnerId()));
        }
        return posts;
    }
    public List<CompletePost> getPostsByOwnerIdRetCompletePost(Integer ownerId) {
        List<Post> posts = postRepository.findByOwnerId(ownerId);
        for (Post post : posts) {
            Post updated = getById(post.getPostId());
            post.setCommentsNumber(updated.getCommentsNumber());
            post.setLikesNumber(updated.getLikesNumber());
            post.setUsername(getUsername(post.getOwnerId()));
        }
        posts.sort(Comparator.comparing(Post::getPostDate).reversed());

        List<CompletePost> postsAndIsLiked = new ArrayList<>();
        for (Post post : posts) {
            CompletePost curr = new CompletePost();
            curr.setPostId( post.getPostId() );
            curr.setPostDate( post.getPostDate() );
            curr.setContent( post.getContent() );
            curr.setOwnerId(post.getOwnerId());
            curr.setImagePath(post.getImagePath());
            curr.setUsername(post.getUsername());
            curr.setCommentsNumber(post.getCommentsNumber());
            curr.setLikesNumber(post.getLikesNumber());
            curr.setLiked( isLiked( curr.getPostId() , ownerId ) );
            curr.setBookMarked( isBookMarked( curr.getPostId() , ownerId ) );
            postsAndIsLiked.add(curr);
        }
        return postsAndIsLiked;
    }


    Integer countComments(Integer postId) {
        System.out.println(postId);
        ResponseEntity<Integer> response = new RestTemplate().getForEntity(COMMENT_COUNT_URL, Integer.class, postId);
        return response.getBody();
    }

    /////////
    String getUsername(Integer ownerId) {
        ResponseEntity<String> response = new RestTemplate().getForEntity(USER_RESPONSE_URL, String.class, ownerId);
        System.out.println(response.getBody()+" :aaaaaaa");
        return response.getBody();
    }

    Integer countLikes(Integer postId) {
        ResponseEntity<Integer> response = new RestTemplate().getForEntity(LIKES_COUNT_URL, Integer.class, postId);
        return response.getBody();
    }


    // display friends posts.
    public List<CompletePost> getFriendsPosts(Integer userId) {
        ResponseEntity<List<Integer>> response = new RestTemplate().exchange(
                FRIEND_IDS_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                userId
        );

        List<Integer> friendIds = response.getBody();
        List<Post> posts = new ArrayList<>();
        assert friendIds != null;
        for (Integer friendId : friendIds) {
            posts.addAll(getPostsByOwnerId(friendId));
        }
        posts.sort(Comparator.comparing(Post::getPostDate).reversed());

        List<CompletePost> postsAndIsLiked = new ArrayList<>();
        for (Post post : posts) {
            CompletePost curr = new CompletePost();
            curr.setPostId( post.getPostId() );
            curr.setPostDate( post.getPostDate() );
            curr.setContent( post.getContent() );
            curr.setOwnerId(post.getOwnerId());
            curr.setImagePath(post.getImagePath());
            curr.setUsername(post.getUsername());
            curr.setCommentsNumber(post.getCommentsNumber());
            curr.setLikesNumber(post.getLikesNumber());
            curr.setLiked( isLiked( curr.getPostId() , userId ) );
            curr.setBookMarked( isBookMarked( curr.getPostId() , userId ) );
            postsAndIsLiked.add(curr);
        }
        return postsAndIsLiked;
    }

    // get bookmarks.
    public List <Integer> getBookmarkIds(Integer userId) {
        ResponseEntity<List<Integer>> response = new RestTemplate().exchange(
                BOOKMARK_IDS_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                userId
        );
        return response.getBody();
    }
    public List<CompletePost> getBookmarks(List<Integer> postsIds,int userId) {

        List<Post> posts = new ArrayList<>();
        for (Integer postId : postsIds) {
            posts.add(getById(postId));
        }
        posts.sort(Comparator.comparing(Post::getPostDate).reversed());

        List<CompletePost> postsAndIsLiked = new ArrayList<>();
        for (Post post : posts) {
            CompletePost curr = new CompletePost();
            curr.setPostId( post.getPostId() );
            curr.setPostDate( post.getPostDate() );
            curr.setContent( post.getContent() );
            curr.setOwnerId(post.getOwnerId());
            curr.setImagePath(post.getImagePath());
            curr.setUsername(post.getUsername());
            curr.setCommentsNumber(post.getCommentsNumber());
            curr.setLikesNumber(post.getLikesNumber());
            curr.setLiked( isLiked( curr.getPostId() , userId ) );
            curr.setBookMarked( isBookMarked( curr.getPostId() , userId ) );
            postsAndIsLiked.add(curr);
        }
        return postsAndIsLiked;
    }

    public Integer getOwnerIdByPostId(Integer postId) {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()) {
            return post.get().getOwnerId();
        }
        throw new RuntimeException("Post not found");
    }

}
