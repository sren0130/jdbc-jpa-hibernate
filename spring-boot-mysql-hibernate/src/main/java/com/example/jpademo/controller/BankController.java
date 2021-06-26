package com.example.jpademo.controller;

import com.example.jpademo.model.User;
import com.example.jpademo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) {
       return userRepository.findById(id).orElse(null);
    }
}
