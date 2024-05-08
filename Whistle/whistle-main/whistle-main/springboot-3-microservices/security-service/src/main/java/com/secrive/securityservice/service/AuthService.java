package com.secrive.securityservice.service;

import com.secrive.securityservice.entity.UserCredential;
import com.secrive.securityservice.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String saveUser(UserCredential credential) throws RuntimeException {

            credential.setPassword(passwordEncoder.encode(credential.getPassword()));
            repository.save(credential);
            return "user added to the system";

    }



    public String generateToken(String username , int id) {
        return jwtService.generateToken(username , id);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

    public Optional<UserCredential> findByEmail(String username){
        return repository.findByEmail(username);
    }
}
