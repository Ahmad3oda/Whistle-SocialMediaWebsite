package com.dailycodebuffer.usermanagementservice.service;
import com.dailycodebuffer.usermanagementservice.model.User;
import com.dailycodebuffer.usermanagementservice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public List< User > findAll(){
        return userRepo.findAll();
    }
    // find
    public User getUser(int id){
        Optional< User > existingUserOptional = userRepo.findById( id );
        if(existingUserOptional.isPresent()) {
            return existingUserOptional.get();
        }
        else{
            throw new RuntimeException("Task not found with id: " + id);
        }
    }
    public User update(int id, User user){
        Optional< User > existingUserOptional = userRepo.findById( id );
        if(existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            if (user.getFirstName() != null) {
                existingUser.setFirstName(user.getFirstName());
            }

            if (user.getLastName() != null) {
                existingUser.setLastName(user.getLastName());
            }

            if (user.getPassword() != null) {
                existingUser.setPassword(user.getPassword());
            }

            if (user.getPhoneNumber() != null) {
                existingUser.setPhoneNumber(user.getPhoneNumber());
            }

            if (user.getCountry() != null) {
                existingUser.setCountry(user.getCountry());
            }

            if (user.getBirthDate() != null) {
                existingUser.setBirthDate(user.getBirthDate());
            }

            if (user.getGender() != null) {
                existingUser.setGender(user.getGender());
            }
            return userRepo.save(existingUser);
        }
        else {
            throw new RuntimeException("Task not found with id: " + id);
        }
    }

    // delete
    public void deleteUser(int id){
        userRepo.deleteById( id );
    }

    public List <User> search(String firstName , String lastName){

        return userRepo.findByFullName(firstName , lastName);
    }
}
