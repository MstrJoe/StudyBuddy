package com.studybuddy.api.controller;

import java.security.Principal;

import com.studybuddy.api.entity.User;
import com.studybuddy.api.payload.responses.UserResponseDto;
import com.studybuddy.api.payload.input.UserUpdateDto;

import com.studybuddy.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PutMapping("/me")
    public ResponseEntity<?> updateMe(Principal principal,
                                      @RequestBody @Valid UserUpdateDto data, BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            return new ResponseEntity<> (bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        User user = this.userService.getByPrincipal(principal);

        User updatedUser = this.userService.update(user, data);

        return new ResponseEntity<>(new UserResponseDto(updatedUser), HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> currentUser(Principal principal) {
        User user = this.userService.getByPrincipal(principal);
        UserResponseDto response = new UserResponseDto(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/avatar")
    public ResponseEntity<?> uploadAvatar(@RequestParam MultipartFile file, Principal principal) {
        User currentUser = this.userService.getByPrincipal(principal);

        try {
            this.userService.uploadAvatar(file, currentUser);
            return new ResponseEntity<>("Upload success", HttpStatus.OK);
        } catch (Exception err) {
            return new ResponseEntity<>("Upload failed", HttpStatus.BAD_REQUEST);
        }
    }
}
