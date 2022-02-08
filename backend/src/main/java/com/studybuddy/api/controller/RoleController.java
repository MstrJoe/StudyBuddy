package com.studybuddy.api.controller;

import com.studybuddy.api.entity.Role;
import com.studybuddy.api.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping()
    public List<Role> getRoles() {
        return this.roleRepository.findAll();
    }

}