package com.studybuddy.api.controller;

import java.security.Principal;

import com.studybuddy.api.entity.User;
import com.studybuddy.api.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/me")
    public User currentUser(Principal principal) {
        return this.userRepository.findByUsernameOrEmail(principal.getName(), principal.getName()).get();
    }
}
