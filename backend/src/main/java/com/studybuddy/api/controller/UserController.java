package com.studybuddy.api.controller;

import java.security.Principal;

import com.studybuddy.api.entity.User;
import com.studybuddy.api.payload.UserUpdateDto;
import com.studybuddy.api.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PutMapping("/me")
    public User updateMe(Principal principal, @Validated @RequestBody UserUpdateDto data) {

        User user = this.userRepository.findByUsernameOrEmail(principal.getName(), principal.getName()).get();

        if (data.getEmail() != null) {
            user.setEmail(data.getEmail().get());
        }

        if (data.getName() != null) {
            user.setName(data.getName().get());
        }

        if (data.getUsername() != null) {
            user.setUsername(data.getUsername().get());
        }

        if (data.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(data.getPassword().get()));
        }

        this.userRepository.save(user);

        return user;
    }

    @GetMapping("/me")
    public Principal currentUser(Principal principal) {

            return principal;

//        return this.userRepository.findByUsernameOrEmail(principal.getName(), principal.getName()).get();
    }
}
