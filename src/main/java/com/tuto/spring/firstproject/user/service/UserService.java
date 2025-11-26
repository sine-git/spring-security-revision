package com.tuto.spring.firstproject.user.service;

import com.sun.jdi.InternalException;
import com.tuto.spring.firstproject.user.UserRepository;
import com.tuto.spring.firstproject.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

     private final UserRepository userRepository;
     private final PasswordEncoder passwordEncoder;

     public ResponseEntity<List<User>> findAll(){
         try {
         List<User> users = this.userRepository.findAll();
         return  ResponseEntity.ok(users);
         } catch (Exception e) {
             e.printStackTrace();
             throw new InternalException(e.getMessage());
         }
     }

    public ResponseEntity<User> create(User user) {

         user.setPassword(passwordEncoder.encode(user.getPassword()));
         return ResponseEntity.ok(this.userRepository.save(user));
    }
}
