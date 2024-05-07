package com.dailycodebuffer.bookmarkservice.repository;

import com.dailycodebuffer.bookmarkservice.entity.Bookmark;
import com.dailycodebuffer.bookmarkservice.entity.BookmarkId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark,BookmarkId> {

    List<Bookmark> findAllByUserId(Integer userId);
    boolean existsById(BookmarkId id);

//    void deleteByUserIdAndPostId(BookmarkId bookmarkId);
    void deleteById(BookmarkId id);
    @Modifying
    @Transactional
    void deleteAllByPostId(Integer postId);
}
