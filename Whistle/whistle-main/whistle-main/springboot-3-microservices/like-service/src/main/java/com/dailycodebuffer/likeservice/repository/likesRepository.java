package com.dailycodebuffer.likeservice.repository;

import com.dailycodebuffer.likeservice.entity.Like;
import com.dailycodebuffer.likeservice.entity.LikeId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface likesRepository extends JpaRepository<Like, LikeId> {
    boolean existsByUserIdAndPostId(int userId,int postId);
    int countByPostId(int postId);
    void deleteById(LikeId id);
    @Transactional
    @Modifying
    void deleteAllByPostId(int postId);
}
