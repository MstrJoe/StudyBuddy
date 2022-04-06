package com.studybuddy.api.controller;

import java.security.Principal;

import com.studybuddy.api.entity.User;
import com.studybuddy.api.payload.responses.UserResponseDto;
import com.studybuddy.api.payload.input.UserUpdateDto;
import com.studybuddy.api.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserResponseDto> updateMe(Principal principal, @Validated @RequestBody UserUpdateDto data) {

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

        return new ResponseEntity<>(new UserResponseDto(user), HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> currentUser(Principal principal) {
        User user = this.userRepository.findByUsernameOrEmail(principal.getName(), principal.getName()).get();
        UserResponseDto response = new UserResponseDto(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
