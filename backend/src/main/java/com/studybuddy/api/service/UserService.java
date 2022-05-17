package com.studybuddy.api.service;

import com.studybuddy.api.entity.User;
import com.studybuddy.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getByPrincipal(Principal principal) {
        User user = this.userRepository.findByUsernameOrEmail(principal.getName(), principal.getName()).get();

        return user;
    }
}
