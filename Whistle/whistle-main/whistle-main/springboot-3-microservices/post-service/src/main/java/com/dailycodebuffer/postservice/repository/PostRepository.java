package com.dailycodebuffer.postservice.repository;

import com.dailycodebuffer.postservice.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByOwnerId(Integer ownerId);
}