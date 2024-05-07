package com.dailycodebuffer.usermanagementservice.repository;

import com.dailycodebuffer.usermanagementservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository <User , Integer> {
    @Query("SELECT u FROM User u WHERE (LOWER(u.firstName) LIKE LOWER(CONCAT(?1, '%')) OR LOWER(u.lastName) LIKE LOWER(CONCAT(?1, '%')))")
    List <User> findByFullName(String firstName, String lastName);}
