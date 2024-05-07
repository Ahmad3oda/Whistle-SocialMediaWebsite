package com.dailycodebuffer.bookmarkservice.service;

import com.dailycodebuffer.bookmarkservice.entity.Bookmark;
import com.dailycodebuffer.bookmarkservice.entity.BookmarkId;
import com.dailycodebuffer.bookmarkservice.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkServices {
    @Autowired
    private BookmarkRepository bookmarkRepository;
//    private final WebClient webClient;
//

    /**
     *
     * @param userId
     * @return List of integers ( id's of posts that user bookmarked)
     */
    public List<Bookmark>getBookmarks(Integer userId){
        return  bookmarkRepository.findAllByUserId(userId);
    }
//

    /**
     *
     * @param postId
     * @param userId
     * @return  boolean is this post is bookmarked by this user or not
     * usage : in frontend
     */
    public boolean isBookmarked(Integer userId, Integer postId){
        BookmarkId bookmarkId = new BookmarkId(userId,postId);
        return bookmarkRepository.existsById( bookmarkId);
    }


//
    /**
     *
     * @param postId
     * @param userId
     * add new bookmark
     */
    public void addBookmark(Integer postId, Integer userId){
        Bookmark bookmark = new Bookmark(userId,postId);
        bookmarkRepository.save(bookmark);
    }

    public void addBookmark(Bookmark bookmark){
        addBookmark(bookmark.getPostId(),bookmark.getUserId());
    }



    public void deleteBookmark(Integer userId, Integer postId) {
        //TODO : Exception Handling using wrapper cladd => AOP
        BookmarkId bookmarkId = new BookmarkId(userId, postId);
        bookmarkRepository.deleteById(bookmarkId);
    }

    public void deleteBookmarksOfPost(Integer postId){

        bookmarkRepository.deleteAllByPostId(postId);
    }

}
