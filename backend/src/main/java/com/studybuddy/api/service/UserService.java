package com.studybuddy.api.service;

import com.studybuddy.api.entity.User;
import com.studybuddy.api.payload.input.UserUpdateDto;
import com.studybuddy.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FileUploadService fileUploadService;

    public User getByPrincipal(Principal principal) {
        User user = this.userRepository.findByUsernameOrEmail(principal.getName(), principal.getName()).get();

        return user;
    }

    public User update(User user, UserUpdateDto data) {
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

    public User uploadAvatar(MultipartFile file, User user) throws Exception {

        try {
            String path = this.fileUploadService.store(file);
            user.setAvatar(path);

            this.userRepository.save(user);

            return user;

        } catch (Exception err) {
            throw new Exception("Upload failed");
        }
    }
}
