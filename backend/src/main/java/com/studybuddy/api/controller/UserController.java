package com.studybuddy.api.controller;

import java.security.Principal;

import com.studybuddy.api.entity.User;
import com.studybuddy.api.payload.responses.UserResponseDto;
import com.studybuddy.api.payload.input.UserUpdateDto;
import com.studybuddy.api.repository.UserRepository;

import com.studybuddy.api.service.FileUploadService;
import com.studybuddy.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FileUploadService fileUploadService;

    @PutMapping("/me")
    public ResponseEntity<?> updateMe(Principal principal,
                                      @RequestBody @Valid UserUpdateDto data, BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            return new ResponseEntity<> (bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        User user = this.userService.getByPrincipal(principal);

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
        User user = this.userService.getByPrincipal(principal);
        UserResponseDto response = new UserResponseDto(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/avatar")
    public ResponseEntity<?> uploadAvatar(@RequestParam MultipartFile file, Principal principal) {
        User currentUser = this.userService.getByPrincipal(principal);

        String path = this.fileUploadService.store(currentUser.getId(), file);

        currentUser.setAvatar(path);

        this.userRepository.save(currentUser);

        return new ResponseEntity<>("Toppie", HttpStatus.OK);
    }
}
