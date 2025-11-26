package com.tuto.spring.firstproject.user.controller;


import com.tuto.spring.firstproject.user.UserRepository;
import com.tuto.spring.firstproject.user.entity.User;
import com.tuto.spring.firstproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "This is the controller for user management" )
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @GetMapping("/free/test")
    String freeTestUser(){
        String stringValue = "Hello world this is the given text";
        return "This is the test user";
    }
    @GetMapping("/secure/test")
    String secureTestUser(){
        String stringValue = "Hello world this is the given text";
        return "This is the test user";
    }


    @GetMapping()
    ResponseEntity<List<User>> findAll(){
        return this.userService.findAll();
    }

    @PostMapping()
    ResponseEntity<User> create(@RequestBody User user){
            return this.userService.create(user);
    }
}
