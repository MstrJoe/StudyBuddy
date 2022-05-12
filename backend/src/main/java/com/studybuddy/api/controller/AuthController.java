package com.studybuddy.api.controller;

import com.studybuddy.api.entity.Role;
import com.studybuddy.api.entity.User;
import com.studybuddy.api.payload.input.LoginDto;
import com.studybuddy.api.payload.input.SignUpDto;
import com.studybuddy.api.payload.responses.UserResponseDto;
import com.studybuddy.api.repository.RoleRepository;
import com.studybuddy.api.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/signout")
    public ResponseEntity<String> signOut() {
        SecurityContextHolder.getContext().setAuthentication(null);

        return new ResponseEntity<>("User signed-out successfully!.", HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginDto loginDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            return new ResponseEntity<> (bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        this.signUserIn(loginDto.getUsernameOrEmail(), loginDto.getPassword());

        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {

        // add check for username exists in a DB
        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            return new ResponseEntity<>("Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in DB
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email is already taken!",
                    HttpStatus.BAD_REQUEST);
        }

        // create user object
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role role = roleRepository.findById(signUpDto.getRoleId()).get();

        user.setRole(role);

        userRepository.save(user);

        // sign user in after account has been created
        this.signUserIn(user.getEmail(), signUpDto.getPassword());

        return new ResponseEntity<UserResponseDto>(new UserResponseDto(user), HttpStatus.OK);
    }

    private void signUserIn(String usernameOrEmail, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usernameOrEmail, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}