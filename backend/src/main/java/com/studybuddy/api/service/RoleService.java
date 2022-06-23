package com.studybuddy.api.service;

import com.studybuddy.api.entity.User;
import com.studybuddy.api.payload.input.UserUpdateDto;
import com.studybuddy.api.payload.responses.RoleResponseDto;
import com.studybuddy.api.repository.RoleRepository;
import com.studybuddy.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FileUploadService fileUploadService;

    public List<RoleResponseDto> getCollection() {
        List<RoleResponseDto> collection = this.roleRepository.findAll().stream().map(item -> new RoleResponseDto(item))
                .collect(Collectors.toList());

        return collection;
    }
}
