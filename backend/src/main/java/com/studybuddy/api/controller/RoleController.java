package com.studybuddy.api.controller;

import com.studybuddy.api.payload.responses.RoleResponseDto;
import com.studybuddy.api.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping()
    public ResponseEntity<List<RoleResponseDto>> getRoles() {
        List<RoleResponseDto> collection = this.roleRepository.findAll().stream().map(item -> new RoleResponseDto(item))
                .collect(Collectors.toList());

        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

}