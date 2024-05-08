package com.secrive.securityservice.repository;

import com.secrive.securityservice.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential , Integer> {
    Optional<UserCredential> findByEmail(String username);
}
