package com.dailycodebuffer.usermanagementservice.controller;

import com.dailycodebuffer.usermanagementservice.model.User;
import com.dailycodebuffer.usermanagementservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user-management")
public class UserController {
    @Autowired
    private UserService userService;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class UserInfo{
        private int id;
        private String firstName;
        private String lastName;
    }

    @GetMapping("/all")
    public List <User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/searchById/{id}")
    public String findUserById(@PathVariable int id){
        User user = userService.getUser(id);
        return (user.getFirstName()+" "+user.getLastName());
    }

    @GetMapping("/searchByName/{name}")
    public List <UserInfo> search(@PathVariable String name){
        String[] parts = name.split(" ");
        String firstName = parts[0];
        String lastName = parts.length > 1 ? parts[1] : "";
        System.out.println(firstName + " " + lastName);
        List <User> users = userService.search(firstName , lastName);
        List <UserInfo> userInfo = new ArrayList<>();
        for (User user : users){
            userInfo.add(new UserInfo(user.getId() , user.getFirstName() , user.getLastName()));
        }
        return userInfo;
    }

    @PutMapping("/update/{id}")
    public User update(@PathVariable int id , @RequestBody User user){
        return userService.update(id , user);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        userService.deleteUser(id);
    }
}
